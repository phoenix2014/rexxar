package com.rexxar.crawler.scheduler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.common.collect.ImmutableList;
import com.rexxar.crawler.boot.CrawlerBooter;
import com.rexxar.crawler.UpdateNovelCrawler;
import com.rexxar.common.global.Configs;
import com.rexxar.dao.CrawlerTaskMapper;
import com.rexxar.dao.model.CrawlerTaskExample;
import com.rexxar.dao.model.CrawlerTaskExample.Criteria;

public class IncrementalNovelScheduler {

	private static Logger logger = LoggerFactory.getLogger(IncrementalNovelScheduler.class);
	
	private static final String SCHEDULER_MAXINCREMENTALTHREAD = "scheduler.maxincrementalthread";
	
	private	Integer schedulerMaxIncrementalThread = Configs.getInt(SCHEDULER_MAXINCREMENTALTHREAD);
	
	@Autowired
	private CrawlerTaskMapper crawlerTaskMapper;
	
	//every after 30mins update novels
	//@Scheduled(fixedDelay = 1800000)
	@Scheduled(fixedDelay = 30000)   //30s
	public void IncrementalUpdate(){
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(schedulerMaxIncrementalThread);
		
		final ImmutableList<Long> needIncrementalUpdateIds = getIncrementalNovelIds();
		
		if (needIncrementalUpdateIds == null) {
			logger.info("No novel to incremental update, select crawler task crawledchapter=0 find 0 novel!");
			return;
		}
				
		for (int i = 0; i < needIncrementalUpdateIds.size(); i++) {
			final Long novelid = needIncrementalUpdateIds.get(i);
			logger.info("incremantal update, process index[{}] :  novel[{}]", i,novelid);
			
			fixedThreadPool.execute(new Runnable() {
				
				public void run() {
					UpdateNovelCrawler updateNovelCrawler = (UpdateNovelCrawler) CrawlerBooter.ctx.getBean("updateNovelCrawler");
					try {
						logger.info("start incremantal update novel [{}]",novelid);
						
						updateNovelCrawler.updateNovel(novelid);
												
						logger.info("end incremantal update novel [{}]",novelid);	
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.error("incremantal update novel [{}] eroor!!!! IOException! ",novelid);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.error("incremantal update novel [{}] eroor!!!! InterruptedException",novelid);
					}
				}
			});
		}
		
		fixedThreadPool.shutdown();
		
		try {
			fixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("Congratulations! Increment batch update {} success!",needIncrementalUpdateIds.size());
	}
	
	//增量更新小说条件是：  抓取章节大于0并且超过1mins没有更新过
	private ImmutableList<Long> getIncrementalNovelIds(){
		//logger.info("in getIncrementalNovelIds");
		CrawlerTaskExample cTaskExample = new CrawlerTaskExample();
		Criteria criteria = cTaskExample.createCriteria();
		criteria.andCrawledChapterGreaterThan((short) 0);
		
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		Date newdate = DateUtils.addMinutes(timeStamp, -1);
		criteria.andUpdateTimeLessThan(newdate);
		
		ArrayList<Long> crawlerIds = (ArrayList<Long>) crawlerTaskMapper.selectIDs(cTaskExample);
		if (crawlerIds.size() == 0) {
			return null;
		}
		
		return ImmutableList.copyOf(crawlerIds);
	}
	
	public void testThread(){
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(schedulerMaxIncrementalThread);
		for (int i = 0; i < 1000; i++) {
			logger.info("incremantal update, process index[{}] ", i);
			final int novelid = i;
			
			fixedThreadPool.execute(new Runnable() {
				
				public void run() {
					logger.info("start incremantal update novel [{}]",novelid);						
					logger.info("end incremantal update novel [{}]",novelid);
				}
			});
		}
		
		fixedThreadPool.shutdown();
		
		try {
			fixedThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("Congratulations! Increment batch update {} success!",1000);
	}
	
	public static void main(String[] argv) throws UnsupportedEncodingException{
//		Date date = new Date();
//		logger.debug(date.toString());
//		Timestamp timeStamp = new Timestamp(date.getTime());
//		Date newdate = DateUtils.addMinutes(timeStamp, -15);
//		logger.debug(newdate.toString());
//		String crawledUrl = "http://www.biquge.la/book/4637/";
//		BqgNovelExtract bqgNovelExtract = new BqgNovelExtract();
//		String crawledHtml = HttpClientUtils.getContent(crawledUrl);
//		if (crawledHtml == null) {
//			logger.error("get {} new novels error",crawledUrl);
//			return;
//		}else{
//			String charset =  RexxarPageUtils.getCharSetByBody(crawledHtml);		
//			String cleanHtml = RexxarPageUtils.changeCharset(crawledHtml, "ISO-8859-1", charset);	
//			Document crawledDoc = Jsoup.parse(cleanHtml);
//			
//			String[] alldir = bqgNovelExtract.getALLDirectory(crawledDoc);
//			
//			logger.info("alldir length: {}", alldir.length);
//			logger.info("{}",StringUtils.join(alldir, "\n"));
//			logger.info("dir count : {}",bqgNovelExtract.getDirectoryCount(crawledDoc));
//		}
		
	}
	
}
