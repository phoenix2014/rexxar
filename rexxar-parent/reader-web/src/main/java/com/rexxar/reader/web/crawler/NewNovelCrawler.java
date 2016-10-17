package com.rexxar.reader.web.crawler;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rexxar.common.global.Configs;
import com.rexxar.common.utils.HttpClientUtils;
import com.rexxar.common.utils.RexxarNovelUtils;
import com.rexxar.common.utils.RexxarPageUtils;
import com.rexxar.common.extract.BqgNovelExtract;
import com.rexxar.dao.CrawlerTaskMapper;
import com.rexxar.dao.NovelDirectoryMapper;
import com.rexxar.dao.NovelOverviewMapper;
import com.rexxar.dao.model.CrawlerTask;
import com.rexxar.dao.model.CrawlerTaskExample;
import com.rexxar.dao.model.CrawlerTaskExample.Criteria;
import com.rexxar.dao.model.NovelDirectoryWithBLOBs;
import com.rexxar.dao.model.NovelOverview;


@Component
public class NewNovelCrawler
{	
	private static Logger logger = LoggerFactory.getLogger(NewNovelCrawler.class);
	
  	private static final String CRAWLE_Filedir = "crawler.filedir";
	public static String crawleFiledir = Configs.getString(CRAWLE_Filedir);
	
	//public static String sourceUrl = "http://www.biquge.la/book/2/";
	
	@Autowired
	private CrawlerTaskMapper crawlerTaskMapper;
	
	@Autowired
	private NovelOverviewMapper novelOverviewMapper;
	
	@Autowired
	private NovelDirectoryMapper novelDirectoryMapper;
	
	@Autowired
	private BqgNovelExtract bqgNovelExtract;
	
	/**
	 * 抓取新的小说，初始化抓取任务表和小说概览表， 写入两个表用事务来管理 v1.0
	 * v2.0 修改点为：  返回小说id， 已完结的照样抓取，  已存在抓取任务表的返回小说id  2016/09/23
	 * @throws IOException 
	 */
	@Transactional()
	public Long CrawleNewNovel(Long userId,String sourceUrl) throws IOException
	{
		String sHtml = HttpClientUtils.getContent(sourceUrl);
		if (sHtml == null) {
			logger.error("user {} trigger {} error, not continue crawl",userId,sourceUrl);
			return -1L;
		}
		
		String charset =  RexxarPageUtils.getCharSetByBody(sHtml);
		String cleanHtml = RexxarPageUtils.changeCharset(sHtml, "ISO-8859-1", charset);
		
		Document novelHtml = Jsoup.parse(cleanHtml);
		
		//小说已完结不主动抓取
//		try {
//			if (bqgNovelExtract.isNovelEnd(novelHtml)) {
//				logger.info("novel [{}] is end, do not take the initiative to crawl", bqgNovelExtract.getName(novelHtml));
//				return;
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
				
		//小说在抓取任务中存在同名，不重复抓取
		CrawlerTaskExample cTaskExample = new CrawlerTaskExample();
		Criteria criteria = cTaskExample.createCriteria();
		criteria.andBooknameEqualTo(bqgNovelExtract.getName(novelHtml));
		ArrayList<CrawlerTask> crawlerTask = (ArrayList<CrawlerTask>) crawlerTaskMapper.selectByExample(cTaskExample);
		if (crawlerTask.size() > 0) {
			logger.info("novel [{}] already in rexxar task, do not repeat crawl", bqgNovelExtract.getName(novelHtml));
			return crawlerTask.get(0).getId();
		}
		
		Long novelId = RexxarNovelUtils.generatorNovelId();
		//创建抓取任务
		initCrawlerTask(novelHtml,novelId,sourceUrl);
		//创建小说概览
		initNovelOverview(novelHtml, novelId);
		//创建小说目录存储
		initNovelDirectory(novelHtml,novelId);
		//创建小说章节内容存储， 直接文件存储  （如果web系统，可以考虑存储为html页面后直接访问）
		initDownloadContentDir(novelId);
		
		logger.info("user {} trigger new novel {} crawler task",userId, novelId);
		
		return novelId;
	}
	
	private void initCrawlerTask(Document novelHtml,Long novelId,String sourceUrl) throws UnsupportedEncodingException
	{
		CrawlerTask ct = new CrawlerTask();
		
		String latestChapterName = bqgNovelExtract.getLatestChapterName(novelHtml);
		String bookname = bqgNovelExtract.getName(novelHtml);
		Short latestChapter = bqgNovelExtract.getDirectoryCount(novelHtml);
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		
		ct.setId(novelId);
		ct.setBookname(bookname);
		ct.setSource("biquge");
		ct.setSourceUrl(sourceUrl);
		ct.setBackSource("");
		ct.setLatestChapter(latestChapter);
		ct.setLatestChapterName(latestChapterName);
		ct.setCrawledChapter((short) 0);
		ct.setCrawledChapterName("");  
		ct.setCrawledPercent(BigDecimal.valueOf(0.0000));     
		ct.setCrawledStatus(true);
		ct.setUpdateTime(timeStamp);
		ct.setCreateTime(timeStamp);
		
		crawlerTaskMapper.insert(ct);
		
		logger.info("novel [{}] initCrawlerTask success", bookname);
	}
	
	private void initNovelOverview(Document novelHtml,Long novelId)
	{
		NovelOverview novelOverview = new NovelOverview();
		
		String bookname = bqgNovelExtract.getName(novelHtml);
		String author = bqgNovelExtract.getAuthor(novelHtml);
		String imgurl = bqgNovelExtract.getImgUrl(novelHtml);
		String describe = bqgNovelExtract.getDescribe(novelHtml);
		String category = bqgNovelExtract.getCagetory(novelHtml);
		String status = bqgNovelExtract.getStatus(novelHtml);
		
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		
		
		novelOverview.setId(novelId);
		novelOverview.setBookname(bookname);
		novelOverview.setAuthor(author);
		novelOverview.setImgUrl(imgurl);
		novelOverview.setNovelDescribe(describe);
		novelOverview.setCategory(category);
		novelOverview.setCrawledChapter((short) 0);
		novelOverview.setCrawledChapterName("");
		novelOverview.setNovelStatus(status);
		novelOverview.setNovelScore(BigDecimal.valueOf(9.9));
		novelOverview.setPublishSource("");
		novelOverview.setPreferGender(true);
		novelOverview.setUpdateTime(timeStamp);
		novelOverview.setCreateTime(timeStamp);		
		
		novelOverviewMapper.insert(novelOverview);  
		
		logger.info("novel [{}] initNovelOverview success", bookname);
	}
	
	public void initNovelDirectory(Document novelHtml,Long novelId)
	{
		NovelDirectoryWithBLOBs novelDirectory = new NovelDirectoryWithBLOBs();
		
		String bookname = bqgNovelExtract.getName(novelHtml);
		
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		
		novelDirectory.setId(novelId);
		novelDirectory.setBookname(bookname);
		novelDirectory.setCreateTime(timeStamp);
		novelDirectory.setUpdateTime(timeStamp);
		novelDirectory.setCrawledChapterNumber((short) 0);
		
		novelDirectoryMapper.insert(novelDirectory);
		
		logger.info("novel [{}] initNovelDirectory success", bookname);
	}
	
	public static void initDownloadContentDir(Long novelId) throws IOException
	{
		String newNovelDirPath = crawleFiledir + File.separatorChar + novelId.toString(); 
		File newNovelDir = new File(newNovelDirPath);
		Boolean result = newNovelDir.mkdirs();
		logger.info("init novel store dir: {} {}", newNovelDirPath, result);
	}
	
	public static void main( String[] args ) 
    {  			
		try {
			initDownloadContentDir(2625414856641831L);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*		BqgNovelExtract bqgExtractor = new BqgNovelExtract();
		bqgExtractor.getName(novelHtml);
		bqgExtractor.getAuthor(novelHtml);
		bqgExtractor.getDescribe(novelHtml);
		bqgExtractor.getImgUrl(novelHtml);
		bqgExtractor.getCagetory(novelHtml);
		bqgExtractor.getLatestChapterName(novelHtml);
		bqgExtractor.getStatus(novelHtml);*/
    }
}
