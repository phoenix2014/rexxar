package com.rexxar.crawler.scheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

/**
 * 在最新小说入库后，会进行一次全量更新
 * 
 * 
 * @author phoenix
 *
 */
public class FullNovelScheduler {
	
	private static Logger logger = LoggerFactory.getLogger(FullNovelScheduler.class);
		
	@Autowired
	private CrawlerTaskMapper crawlerTaskMapper;
	
	private static final String SCHEDULER_MAXFULLTHREAD = "scheduler.maxfullthread";
	
	private	Integer schedulerMaxFullThread = Configs.getInt(SCHEDULER_MAXFULLTHREAD);
	
	//every after 30mins full update novels
	//@Scheduled(fixedDelay = 1800000)
	@Scheduled(fixedDelay = 30000)   //after 30s
	public void fullUpdate(){
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(schedulerMaxFullThread);
		
		final ImmutableList<Long> needFullUpdateIds = getFullNovelid();
		if (needFullUpdateIds == null) {
			logger.info("No novel to full update, select crawler task crawledchapter=0 find 0 novel!");
			return;
		}
		
		for (int i = 0; i < needFullUpdateIds.size(); i++) {
			final Long novelid = needFullUpdateIds.get(i);
			logger.info("full update, process index[{}] :  novel[{}]", i,novelid);
			
			fixedThreadPool.execute(new Runnable() {
				
				//超出SCHEDULER_MAXFULLTHREAD个数的其他线程可能会等待很久，需要注意时效性
				public void run() {
					UpdateNovelCrawler updateNovelCrawler = (UpdateNovelCrawler) CrawlerBooter.ctx.getBean("updateNovelCrawler");
					try {
						logger.info("start full update novel [{}]",novelid);
						
						updateNovelCrawler.updateNovel(novelid);
						
						logger.info("end full update novel [{}]",novelid);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.error("full update novel [{}] eroor!!!! IOException! ",novelid);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.error("full update novel [{}] eroor!!!! InterruptedException",novelid);
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
				
		logger.info("Congratulations! full batch update {} success!",needFullUpdateIds.size());
	}
	
	//全量更新小说的条件是  已抓取章节为0
	private ImmutableList<Long> getFullNovelid() {
		
		CrawlerTaskExample cTaskExample = new CrawlerTaskExample();
		Criteria criteria = cTaskExample.createCriteria();
		criteria.andCrawledChapterEqualTo((short) 0);
		
		ArrayList<Long> crawlerIds = (ArrayList<Long>) crawlerTaskMapper.selectIDs(cTaskExample);
		if (crawlerIds.size() == 0) {
			return null;
		}
		
		return ImmutableList.copyOf(crawlerIds);
	}
}
