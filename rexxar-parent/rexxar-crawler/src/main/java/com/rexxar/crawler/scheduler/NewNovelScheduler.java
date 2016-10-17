package com.rexxar.crawler.scheduler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rexxar.crawler.NewNovelCrawler;
import com.rexxar.common.utils.HttpClientUtils;
import com.rexxar.common.utils.RexxarPageUtils;

/**
 * 抓取新的小说信息入库
 * 
 * v1.0：  自由抓取biquge上面所有的小说
 * v2.0:   抓取模式升级，   用户搜索的小说才抓取，   因为阿里云磁盘成本较大        
 *         disable最新小说抓取  2016/9/12
 * @author phoenix
 *
 */
public class NewNovelScheduler {
	
	private static Logger logger = LoggerFactory.getLogger(NewNovelScheduler.class);
	
	public static final String BQG_PREX = "http://www.biquge.la";
	public static final String BQG_XUANHUAN_URL = "http://www.biquge.la/xuanhuanxiaoshuo";
	public static final String BQG_XIUZHEN_URL = "http://www.biquge.la/xiuzhenxiaoshuo";
	public static final String BQG_DUSHI_URL = "http://www.biquge.la/dushixiaoshuo";
	public static final String BQG_LISHI_URL = "http://www.biquge.la/lishixiaoshuo";
	public static final String BQG_WANGYOU_URL = "http://www.biquge.la/wangyouxiaoshuo";
	public static final String BQG_KEHUAN_URL = "http://www.biquge.la/kehuanxiaoshuo";
	public static final String[] BQG_CATEGORY_URL = {BQG_XUANHUAN_URL,BQG_XIUZHEN_URL,
	                                                 BQG_DUSHI_URL,BQG_LISHI_URL,
	                                                 BQG_WANGYOU_URL,BQG_KEHUAN_URL};
		
	@Autowired
	private NewNovelCrawler newNovelCrawler;
	
	//every after 30mins get new novel
	//@Scheduled(fixedDelay = 1800000)
	//@Scheduled(fixedDelay = 900000)   // v2.0 不再主动抓取新小说
	public void FindNewNovels() throws IOException, InterruptedException{
		Pair<String[], String[]> novelsInfo = getBqgNewNovels();
		if (novelsInfo == null) {
			logger.error("find bqg full novels error! quit FindNewNovels");
			return;
		}
		String[] booknames = novelsInfo.getLeft();
		String[] bookurls = novelsInfo.getRight();
		//logger.debug(StringUtils.join(booknames, ":"));
		//logger.debug(StringUtils.join(bookurls, ":"));
		
		int crawledCount = booknames.length;
		for (int i = 0; i < crawledCount; i++) {
			newNovelCrawler.CrawleNewNovel(bookurls[i]);
			logger.info("Welcome new novel : {}  [{}]", booknames[i],bookurls[i]);
			Thread.sleep(500);
		}
	}
	
	public Pair<String[], String[]> getBqgNewNovels() throws UnsupportedEncodingException{
		String[] booknames = null;
		String[] bookurls = null;
		String[] tmpnames = null;
		String[] tmpurls = null;
		
		for (int i = 0; i < BQG_CATEGORY_URL.length; i++) {
			tmpnames = booknames;
			tmpurls = bookurls;
			Pair<String[], String[]> tmpNovels = getBqgCategoryNewNovels(BQG_CATEGORY_URL[i]);
			if (tmpNovels != null) {
				booknames = ArrayUtils.addAll(tmpnames, tmpNovels.getLeft());
				bookurls = ArrayUtils.addAll(tmpurls, tmpNovels.getRight());
			}
		}
		
		if (booknames != null || booknames.length == 0) {
			return Pair.of(booknames, bookurls);
		}else{
			return null;
		}
	}
	
	private Pair<String[], String[]> getBqgCategoryNewNovels(String categoryUrl) throws UnsupportedEncodingException{
		String catetoryHtml = HttpClientUtils.getContent(categoryUrl);
		if (catetoryHtml == null) {
			logger.error("get {} new novels error",categoryUrl);
			return null;
		}else{
			String charset =  RexxarPageUtils.getCharSetByBody(catetoryHtml);		
			String cleanHtml = RexxarPageUtils.changeCharset(catetoryHtml, "ISO-8859-1", charset);	
			Document catetoryDoc = Jsoup.parse(cleanHtml);
			
			Elements newNovels = catetoryDoc.select(".l ul li .s2 a");
			
			int novelsCount = newNovels.size();
			logger.info("total get biquge [{}] novels [{}]", novelsCount,categoryUrl);
			if (novelsCount == 0) {
				return null;
			}
			
			String[] booknames = new String[novelsCount];
			String[] bookurls = new String[novelsCount];
			for (int i = 0; i < novelsCount; i++) {
				booknames[i] = newNovels.get(i).text();
				bookurls[i] = BQG_PREX + "/" + newNovels.get(i).attr("href");
			}
			
			return Pair.of(booknames, bookurls);
		}		
	}
	
	public Pair<String[], String[]> getBqgFullNovels() throws UnsupportedEncodingException 	{
		
		String sHtml = HttpClientUtils.getContent("http://www.biquge.la/xiaoshuodaquan/");
		if (sHtml == null) {
			logger.error("getContent http://www.biquge.la/xiaoshuodaquan/  error!");
			return null;
		}
		String charset =  RexxarPageUtils.getCharSetByBody(sHtml);		
		String cleanHtml = RexxarPageUtils.changeCharset(sHtml, "ISO-8859-1", charset);
		
		Document novelHtml = Jsoup.parse(cleanHtml);
		Elements novels = novelHtml.select("#main li a");
		
		int novelsCount = novels.size();
		logger.info("total get biquge [{}] novels", novelsCount);
		if (novelsCount == 0) {
			return null;
		}
		
		String[] booknames = new String[novelsCount];
		String[] bookurls = new String[novelsCount];
		for (int i = 0; i < novelsCount; i++) {
			booknames[i] = novels.get(i).text();
			bookurls[i] = BQG_PREX + "/" + novels.get(i).attr("href");
		}
		
		return Pair.of(booknames, bookurls);		
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		NewNovelScheduler newNovelScheduler = new NewNovelScheduler();
		Pair<String[], String[]> novelsInfo = newNovelScheduler.getBqgNewNovels();
		
		String[] booknames = novelsInfo.getLeft();
		String[] bookurls = novelsInfo.getRight();
		logger.debug("booknames length : {}", booknames.length);
		logger.debug(StringUtils.join(booknames, ":"));
		logger.debug("bookurls length: {}",bookurls.length);
		logger.debug(StringUtils.join(bookurls, ":"));
	}
}
