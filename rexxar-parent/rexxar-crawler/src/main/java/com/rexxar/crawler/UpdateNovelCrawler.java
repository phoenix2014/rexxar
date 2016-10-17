package com.rexxar.crawler;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.io.Files;
import com.google.common.math.IntMath;
import static com.google.common.base.Preconditions.checkNotNull;

import com.rexxar.common.global.Configs;
import com.rexxar.crawler.common.GlobalConstant;
import com.rexxar.common.utils.HttpClientUtils;
import com.rexxar.common.utils.RexxarNovelUtils;
import com.rexxar.common.utils.RexxarPageUtils;
import com.rexxar.common.extract.BqgNovelExtract;
import com.rexxar.dao.CrawlerTaskMapper;
import com.rexxar.dao.NovelDirectoryMapper;
import com.rexxar.dao.NovelOverviewMapper;
import com.rexxar.dao.model.CrawlerTask;
import com.rexxar.dao.model.NovelDirectoryWithBLOBs;
import com.rexxar.dao.model.NovelOverview;



/**
 * 更新已有的小说的最新章节
 * 
 * 
 *
 */
@Component
@Scope("prototype")   //每次调用产生一个新的实例
public class UpdateNovelCrawler
{	
	private static Logger logger = LoggerFactory.getLogger(UpdateNovelCrawler.class);
	
	private static final String CRAWLE_CHAPTER_STEP = "crawler.chapterStep";
	private static final String CRAWLE_FILEDIR = "crawler.filedir";
	private static final String CRAWLE_CHAPTERINTERVALTIME = "crawler.chapterIntervalTime";
	
//	private static final String DIRECTORY_SPLIT_NUM = "novel.directorySplitNum";
//	private static final String DIRECTORY_SPLIT_NUM2 = "novel.directorySplitNum2";
//	private static final String DIRECTORY_MAX_NUM = "novel.directoryMaxNum";
	private static final String DIRECTORY_LATEST_NUM = "novel.directoryLatestNum";
	
	//每抓取 crawleChapterStep 章节小说后再写入数据库
	public static Integer crawleChapterStep = Configs.getInt(CRAWLE_CHAPTER_STEP);
	
	public static String crawleFiledir = Configs.getString(CRAWLE_FILEDIR);
	
	//每个章节的抓取间隔
	public static Long crawleChapterIntervalTime = Configs.getLong(CRAWLE_CHAPTERINTERVALTIME);
	
//	public static Integer directorySplitNum = Configs.getInt(DIRECTORY_SPLIT_NUM);
//	public static Integer directorySplitNum2 = Configs.getInt(DIRECTORY_SPLIT_NUM2);
//	public static Integer directoryMaxNum = Configs.getInt(DIRECTORY_MAX_NUM);
	
	public static Integer directoryLatestNum = Configs.getInt(DIRECTORY_LATEST_NUM);

	
	@Autowired
	private CrawlerTaskMapper crawlerTaskMapper;
	
	@Autowired
	private NovelOverviewMapper novelOverviewMapper;
	
	@Autowired
	private NovelDirectoryMapper novelDirectoryMapper;
	
	@Autowired
	private BqgNovelExtract bqgNovelExtract;
	
	
	public int updateNovel(Long novelId) throws IOException, InterruptedException
	{
		CrawlerTask crawlerTask = crawlerTaskMapper.selectByPrimaryKey(novelId);
		if (crawlerTask == null) {
			logger.error("when update novel,select novelid = {} null,exit ",novelId);
			return -1;
		}
		
		String novelName = crawlerTask.getBookname();
		short crawledChapter = crawlerTask.getCrawledChapter();
		String crawlerUrl = crawlerTask.getSourceUrl();
		
		String sHtml = HttpClientUtils.getContent(crawlerUrl);
		if (sHtml == null) {
			logger.error("getContent {} error, quit crawle {}",crawlerUrl,novelName);
			return -2;
		}
		String charset =  RexxarPageUtils.getCharSetByBody(sHtml);		
		String cleanHtml = RexxarPageUtils.changeCharset(sHtml, "ISO-8859-1", charset);
		
		Document novelHtml = Jsoup.parse(cleanHtml); 
		
		String novelStatus = bqgNovelExtract.getStatus(novelHtml);
		String newChapterName = bqgNovelExtract.getLatestChapterName(novelHtml);
		short newDirCount = bqgNovelExtract.getDirectoryCount(novelHtml);
		if (newDirCount > crawledChapter) {  // 有最新章节更新
			logger.info("crawler novel {} find new chapter number {} ,new chapter name {}", novelName, newDirCount,newChapterName);	
			int crawledNum = 0;					
			
			Pair<String[], String[]> dirsAndUrls = bqgNovelExtract.getDirsAndUrls(novelHtml, crawlerUrl);
			final String[] allChapterNames = dirsAndUrls.getLeft();
			final String[] allChapterUrls = dirsAndUrls.getRight();
			
			int stepCount = (newDirCount - crawledChapter) / crawleChapterStep;
			if (stepCount > 0) {//对最新更新章节数前crawleChapterStep整数倍的章节， 采用如下方案更新
				int batchChapterNum = crawledChapter + stepCount * crawleChapterStep;
				
				for (int i = crawledChapter; i < batchChapterNum; i++) {
					//step 1 : create novel chapter file
					if (createNovelChapterFile(novelId,allChapterNames[i],allChapterUrls[i])) {
						//每读取crawleChapterStep才更新数据库
						crawledNum++;
						if (IntMath.mod(crawledNum, crawleChapterStep) == 0) {
							UpdateCrawlAndNovelDb(0,novelId,allChapterNames,novelStatus,newDirCount,newChapterName,(short) i);
						}
					} else{
						logger.error("createNovelChapterFile {} [{}]error! quit crawl {}:{}",
								allChapterNames[i],allChapterUrls[i],novelId,novelName);
						return -3;
					}
						
					Thread.sleep(crawleChapterIntervalTime);
				}
				
				crawledChapter = (short) batchChapterNum;  //update crawledChapter to batched new chapter
			}
			
			//对剩下不足crawleChapterStep整数倍的章节，每更新一章就写文件和写数据库
			if(IntMath.mod(newDirCount - crawledChapter, crawleChapterStep) != 0){		
				for (int i = crawledChapter; i < newDirCount; i++) {	
					if(createNovelChapterFile(novelId,allChapterNames[i],allChapterUrls[i]))
					{
						UpdateCrawlAndNovelDb(1,novelId,allChapterNames,novelStatus,newDirCount,newChapterName,(short) i);
					}else{
						logger.error("createNovelChapterFile {} [{}]error! quit crawl {}:{}",
								allChapterNames[i],allChapterUrls[i],novelId,novelName);
						return -3;
					}
					Thread.sleep(crawleChapterIntervalTime);
				}
			}
		}else {
			logger.info("crawler novel {} find no update",novelName);
			return 2;
		}
		
		return 0;
	}
	
	/**
	 * 为了事务管理，更新数据库操作单独剥离出来
	 * @param type
	 * @param novelId
	 * @param allChapterNames
	 * @param novelStatus
	 * @param newDirCount
	 * @param newChapterName
	 * @param crawledChapter
	 */
	@Transactional
	private void UpdateCrawlAndNovelDb(int type,Long novelId,final String[] allChapterNames,String novelStatus,
			short newDirCount,String newChapterName,
			short crawledChapter)
	{		
		if(type == 0){  //批量更新，批量步长为  crawleChapterStep
			updateCrawlerTask(novelId, newDirCount, newChapterName, (short)(crawledChapter+1), allChapterNames[crawledChapter]);
			updateNovelOverview(novelId,(short)(crawledChapter + 1),allChapterNames[crawledChapter],novelStatus);

			String[] crawledChapterArray =  ArrayUtils.subarray(allChapterNames, crawledChapter + 1 - crawleChapterStep, crawledChapter + 1);
			String[] newLatestArray =  ArrayUtils.subarray(allChapterNames, 
					(crawledChapter + 1 - directoryLatestNum) > 0 ? (crawledChapter + 1 - directoryLatestNum) : 0,  crawledChapter + 1);	
			updateNovelDirctory(novelId, crawledChapterArray, newLatestArray);
		}else if (type == 1) { //单章节写入
			updateCrawlerTask(novelId, newDirCount, newChapterName, (short)(crawledChapter+1), allChapterNames[crawledChapter]);
			updateNovelOverview(novelId,(short)(crawledChapter+1),allChapterNames[crawledChapter],novelStatus);

			String[] crawledChapterArray =  ArrayUtils.subarray(allChapterNames, crawledChapter , crawledChapter + 1);	//only one chapter	
			String[] newLatestArray =  ArrayUtils.subarray(allChapterNames, 
					(crawledChapter + 1 - directoryLatestNum) > 0 ? (crawledChapter + 1 - directoryLatestNum) : 0,  crawledChapter + 1);	
			updateNovelDirctory(novelId, crawledChapterArray, newLatestArray);
		}

	}
	
	/**
	 * 创建小说章节文件
	 * @param novelId
	 * @param chapterName
	 * @param chapterUrl
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private boolean createNovelChapterFile(Long novelId, String chapterName, String chapterUrl) throws IOException, InterruptedException
	{
		String newHtml = HttpClientUtils.getContent(chapterUrl);
		if (newHtml == null) {
			logger.error("crawler chaptername [{}], crawler url: [{}] fail! content is null, not crawle!",
					chapterName,chapterUrl);
			return false;
		}
		
		//logger.info("crawler chaptername [{}], crawler url: [{}]", chapterName,chapterUrl);
		
		String charset =  RexxarPageUtils.getCharSetByBody(newHtml);		
		String cleanContentHtml = RexxarPageUtils.changeCharset(newHtml, "ISO-8859-1", charset);

		Document contentHtml = Jsoup.parse(cleanContentHtml);
		String novelContent = bqgNovelExtract.getNovelChapterContent(contentHtml);
		
		String pathName = crawleFiledir + File.separatorChar + novelId.toString() + File.separatorChar + 
				RexxarNovelUtils.generatorNovelContentFilename(chapterName);
		File contentFile = new File(pathName);
		Files.write(novelContent.getBytes(), contentFile);
		
		logger.info("write novel [{}] file:  crawled chaptername [{}] to file [{}]",
				novelId,chapterName,pathName);
		
		return true;
		
	}
	
	/**
	 * 每抓取指定章节数后更新抓取任务表
	 * @param novelId
	 * @param latestChapter
	 * @param latestChapterName
	 * @param crawledChapter
	 * @param crawledChapterName
	 */
	private void updateCrawlerTask(Long novelId,short latestChapter,String latestChapterName,
			short crawledChapter,String crawledChapterName)
	{
		CrawlerTask crawlerTask = crawlerTaskMapper.selectByPrimaryKey(novelId);
		checkNotNull(crawlerTask);
		
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
			
		//Todo
		//用不着每次都更新这个，为了简单，先这样
		crawlerTask.setLatestChapter(latestChapter);
		crawlerTask.setLatestChapterName(latestChapterName);
		
		crawlerTask.setCrawledChapter(crawledChapter);
		crawlerTask.setCrawledChapterName(crawledChapterName);
		crawlerTask.setCrawledPercent( BigDecimal.valueOf((double)crawledChapter/(double)latestChapter) );
		crawlerTask.setCrawledStatus(true);
		crawlerTask.setUpdateTime(timeStamp);
		
		crawlerTaskMapper.updateByPrimaryKey(crawlerTask);
		
		logger.info("update crawler task: novel [{}], new crawled chapter num [{}], crawled chapter name[{}]",
				novelId,crawledChapter,crawledChapterName);
		
	}
	
	/**
	 * 每抓取指定章节数后更新小说概览表
	 * @param novelId
	 * @param crawledChapter
	 * @param crawledChapterName
	 * @param novelStatus
	 */
	private void updateNovelOverview(Long novelId,short crawledChapter,
			String crawledChapterName,String novelStatus)
	{
		NovelOverview novelOverview = novelOverviewMapper.selectByPrimaryKey(novelId);
		checkNotNull(novelOverview);
		
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		
		novelOverview.setCrawledChapter(crawledChapter);
		novelOverview.setCrawledChapterName(crawledChapterName);
		novelOverview.setNovelStatus(novelStatus);
		novelOverview.setUpdateTime(timeStamp);
		
		novelOverviewMapper.updateByPrimaryKey(novelOverview);
		
		logger.info("update novel [{}] overview: new crawled chapter num [{}], crawled chapter name[{}]",novelId,crawledChapter,crawledChapterName);
		
	}
	
	/**
	 * 更新小说目录存储表
	 * @param novelId
	 * @param crawledChapterArray
	 * @param newLatestArray
	 */
	private void updateNovelDirctory(Long novelId, String[] crawledChapterArray, String[] newLatestArray)
	{
		String newCrawledText = StringUtils.join(crawledChapterArray, GlobalConstant.DIRECTORYSPLIT);
		String newLatestText = StringUtils.join(newLatestArray, GlobalConstant.DIRECTORYSPLIT);
		
		NovelDirectoryWithBLOBs novelDirectoryWithBLOBs = novelDirectoryMapper.selectByPrimaryKey(novelId);
		checkNotNull(novelDirectoryWithBLOBs);
		
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		
		short crawledChapterNum = novelDirectoryWithBLOBs.getCrawledChapterNumber();
		
		String text = novelDirectoryWithBLOBs.getText();
		String newtext = null;
		if (text != null) {
			newtext = text + GlobalConstant.DIRECTORYSPLIT + newCrawledText;
		}else{
			newtext = newCrawledText;
		}
				
		novelDirectoryWithBLOBs.setText(newtext);
		
/*		short textCount = novelDirectoryWithBLOBs.getTextCount();
						
		int newCrawledNum = crawledChapterArray.length;
		if(crawledChapterNum + newCrawledNum <= directorySplitNum){
			textCount = 1;
			String newtext1 = null;
			String text1 = novelDirectoryWithBLOBs.getText1();
			if (text1 != null) {
				newtext1 = text1 + GlobalConstant.DIRECTORYSPLIT + newCrawledText;
			}else{
				newtext1 = newCrawledText;
			}			
			novelDirectoryWithBLOBs.setText1(newtext1);
			novelDirectoryWithBLOBs.setTextCount(textCount);			
		}else if (crawledChapterNum + newCrawledNum <= directorySplitNum2) {
			textCount = 2;
			String newtext2 = null;
			String text2 = novelDirectoryWithBLOBs.getText2();
			if (text2 != null) {
				newtext2 = text2 + GlobalConstant.DIRECTORYSPLIT + newCrawledText;
			} else {
				newtext2 = newCrawledText;
			}
			novelDirectoryWithBLOBs.setText2(newtext2);
			novelDirectoryWithBLOBs.setTextCount(textCount);	
		}else if (crawledChapterNum + newCrawledNum <= directoryMaxNum) {
			textCount = 3;
			String newtext3 = null;
			String text3 = novelDirectoryWithBLOBs.getText3();
			if (text3 != null) {
				newtext3 = text3 + GlobalConstant.DIRECTORYSPLIT + newCrawledText;
			} else {
				newtext3 = newCrawledText;
			}
			novelDirectoryWithBLOBs.setText3(newtext3);
			novelDirectoryWithBLOBs.setTextCount(textCount);
		}
*/
		
		int newCrawledNum = crawledChapterArray.length;
		
		novelDirectoryWithBLOBs.setCrawledChapterNumber((short) (crawledChapterNum + newCrawledNum));
		novelDirectoryWithBLOBs.setLatestText(newLatestText);
		novelDirectoryWithBLOBs.setUpdateTime(timeStamp);
		
		novelDirectoryMapper.updateByPrimaryKeyWithBLOBs(novelDirectoryWithBLOBs);
		
		//logger.debug("update novel directory: number[{}] newCrawledText[{}] newLatestText[{}]",newCrawledNum,newCrawledText,newLatestText);
		logger.info("update novel [{}] directory number[{}]",novelId,newCrawledNum);

	}

}
