package com.rexxar.crawler.boot;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rexxar.common.global.Configs;

import com.google.common.io.Files;

public class CrawlerBooter {
	
	private static final Logger logger = LoggerFactory.getLogger(CrawlerBooter.class);
  	private static final String CRAWLE_Filedir = "crawler.filedir";
  
  	public static ClassPathXmlApplicationContext ctx;
	  
	  private static synchronized void initialize() {
	    try {
	    	String location = "spring/spring-config.xml";
	    	ctx = new ClassPathXmlApplicationContext(new String[] {location});
	    	
	    	//create novel content download dir
	    	String crawleFiledir = Configs.getString(CRAWLE_Filedir);
	    	File crawDownloadDir = new File(crawleFiledir);
	    	Files.createParentDirs(crawDownloadDir);
	    	
	    } catch (Exception e) {
	    	logger.error(e.getMessage(), e);
	    	e.printStackTrace();
	    }
	  }
	
	public static void main(String[] args) throws InterruptedException, IOException
	{
		initialize();
		
//		NewNovelScheduler newNovelScheduler = (NewNovelScheduler) ctx.getBean("newNovelScheduler");
//		newNovelScheduler.FindNewNovels();
		
//		FullNovelScheduler fullNovelScheduler = (FullNovelScheduler) ctx.getBean("fullNovelScheduler");
//		fullNovelScheduler.fullUpdate();
		
//		IncrementalNovelScheduler incrementalNovelScheduler = (IncrementalNovelScheduler) ctx.getBean("incrementalNovelScheduler");
//		incrementalNovelScheduler.IncrementalUpdate();
		//incrementalNovelScheduler.testThread();
		
//	    Runtime.getRuntime().addShutdownHook(new Thread() {
//
//	        @Override
//	        public void run() {
//	        	logger.info("crawler stopped");
//	        }
//
//	      });
//	    
//	    Thread.sleep(Integer.MAX_VALUE);
	    
	}
}
