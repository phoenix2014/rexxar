package com.rexxar.reader.web.onlinesearch;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rexxar.common.utils.HttpClientUtils;
import com.rexxar.common.utils.RexxarPageUtils;

public class BqgNovelOnlineSearch implements NovelOnlineSearch {
	
	private static final Logger logger = LoggerFactory.getLogger(BqgNovelOnlineSearch.class);
	
	public static String bqgSearchUrl = "http://zhannei.baidu.com/cse/search?s=7138806708853866527&q=";
	
	@Override
	public HashMap<String, String> searchNovel(String bookname) throws UnsupportedEncodingException {
		String searchUrl = bqgSearchUrl + bookname;
		String sHtml = HttpClientUtils.getContent(searchUrl);
		if (sHtml == null) {
			logger.error("search {} error",searchUrl);
			return null;
		}
		
		String charset =  RexxarPageUtils.getCharSetByBody(sHtml);		
		String cleanHtml = RexxarPageUtils.changeCharset(sHtml, "ISO-8859-1", charset);
		
		Document searchHtml = Jsoup.parse(cleanHtml); 
		Elements eles = searchHtml.select(".result-item .result-item-title a");
		
		int resultLength = eles.size();
		if (resultLength == 0) {
			return null;
		}
		
		HashMap<String, String> searchResults = new HashMap<>();
		
		for (int i = 0; i < resultLength; i++) {
			searchResults.put(eles.get(i).attr("title"), eles.get(i).attr("href"));
		}
		
		return searchResults;
	}
	
	public static void main(String[] args) {
		BqgNovelOnlineSearch bqgNovelOnlineSearch = new BqgNovelOnlineSearch();
		try {
			logger.info(bqgNovelOnlineSearch.searchNovel("蛊真人").toString() );
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
