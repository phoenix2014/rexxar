package com.rexxar.common.extract;


import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.tuple.Pair;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rexxar.common.global.Configs;
import com.rexxar.common.utils.RexxarDateUtils;
import com.rexxar.common.utils.RexxarPageUtils;


/**
 * 笔趣阁抓取策略
 * @author phoenix
 *
 */
@Component
public class BqgNovelExtract implements NovelExtract {

	private static Logger logger = LoggerFactory.getLogger(BqgNovelExtract.class);
	
	private static final String UPDATE_BETWEEN_NOW = "crawler.updateTimeBetweenNow";
	
	public static Integer updateBetweenNow = Configs.getInt(UPDATE_BETWEEN_NOW);
	
	public String getName(Document novelHtml) {
		Elements novelName = novelHtml.select("meta[property=og:novel:book_name]");
		for (Element bookname : novelName) {
			logger.debug("bookname: " + bookname.attr("content"));
			return bookname.attr("content");
		}
		return null;
	}

	public String getAuthor(Document novelHtml) {
		Elements novelName = novelHtml.select("meta[property=og:novel:author]");
		for (Element bookname : novelName) {
			logger.debug("author: " + bookname.attr("content"));
			return bookname.attr("content");
		}
		return null;
	}

	public String getImgUrl(Document novelHtml) {
		Elements novelName = novelHtml.select("meta[property=og:image]");
		for (Element bookname : novelName) {
			logger.debug("imgurl: " + bookname.attr("content"));
			return bookname.attr("content");
		}
		return null;
	}

	public String getDescribe(Document novelHtml) {
		Elements novelName = novelHtml.select("meta[property=og:description]");
		for (Element bookname : novelName) {
			logger.debug("describe: " + bookname.attr("content"));
			return bookname.attr("content");
		}
		return null;
	}

	public String getCagetory(Document novelHtml) {
		Elements novelName = novelHtml.select("meta[property=og:novel:category]");
		for (Element bookname : novelName) {
			logger.debug("category: " + bookname.attr("content"));
			return bookname.attr("content");
		}
		return null;
	}

	public String getLatestChapterName(Document novelHtml) throws UnsupportedEncodingException {
		Elements novelName = novelHtml.select("meta[property=og:novel:latest_chapter_name]");
		for (Element bookname : novelName) {
			logger.debug("latest chapter name: " + bookname.attr("content"));
			return RexxarPageUtils.changeCharset(bookname.attr("content"), "utf8") ;
		}
		return null;
	}

	public String getStatus(Document novelHtml) {
		Elements novelName = novelHtml.select("meta[property=og:novel:status]");
		for (Element bookname : novelName) {
			logger.debug("status: " + bookname.attr("content"));
			return bookname.attr("content");
		}
		return null;
	}

	public short getDirectoryCount(Document novelHtml) {
		Elements novelDirectory = novelHtml.select("#list dd a");
		return (short) novelDirectory.size();
	}

	public String[] getALLDirectory(Document novelHtml) {
		Elements novelDirectory = novelHtml.select("#list dd a");
		
		Integer dirCount = novelDirectory.size();
		String[] dirs = new String[dirCount];
		
		for (int i = 0; i < dirCount; i++) {
			dirs[i] = novelDirectory.get(i).text();
		}
		
		return dirs;
	}

	public Pair<String[], String[]> getDirsAndUrls(Document novelHtml,String prexUrl) {
		Elements novelDirectory = novelHtml.select("#list dd a");
		
		Integer dirCount = novelDirectory.size();
		String[] dirs = new String[dirCount];
		String[] urls = new String[dirCount];
		
		for (int i = 0; i < dirCount; i++) {
			dirs[i] = novelDirectory.get(i).text();
			urls[i] = prexUrl + "/" + novelDirectory.get(i).attr("href");
		}
		
		return Pair.of(dirs, urls);
	}

	public String getNovelChapterContent(Document novelContentHtml) {
		Elements chapterContents = novelContentHtml.select("#content");
		for (Element chapterContent : chapterContents) {
			logger.debug("chapter content: " + chapterContent.text());
			try {
				return RexxarPageUtils.changeCharset(chapterContent.text(), "utf8") ;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public String getLatestNovelUpdateTime(Document novelHtml) {
		Elements updateTimes = novelHtml.select("meta[property=og:novel:update_time]");
		for (Element updateTime : updateTimes) {
			logger.debug("updateTime: " + updateTime.attr("content"));
			return updateTime.attr("content");
		}
		return null;
	}	
	
	public boolean isNovelEnd(Document novelHtml) throws ParseException{
		String latestNovelUpdateTime = getLatestNovelUpdateTime(novelHtml);
		if(latestNovelUpdateTime == null){
			logger.warn("latestNovelUpdateTime is null,not update. novelhtml[{}]",novelHtml.toString());
			return true;
		}
		
		if (StringUtils.equals(getStatus(novelHtml), "完结") || 
				isUpdateTimeExpired(latestNovelUpdateTime)) {
			return true;
		}else{
			return false;
		}
	}
	
	private boolean isUpdateTimeExpired(String latestNovelUpdateTime) throws ParseException{		
		String currentTimeString = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		
		Date latestNovelDate = DateUtils.parseDate(latestNovelUpdateTime, "yyyy-MM-dd HH:mm");
		String latestNovelDateString = DateFormatUtils.format(latestNovelDate, "yyyy-MM-dd");
		logger.debug("latestNovelDateString: {}", latestNovelDateString);
		
		if(RexxarDateUtils.subTwoDays(currentTimeString, latestNovelDateString) >  updateBetweenNow){
			return true;
		}else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		BqgNovelExtract bqgNovelExtract = new BqgNovelExtract();
		try {
			bqgNovelExtract.isUpdateTimeExpired("2015-11-06 17:02");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
