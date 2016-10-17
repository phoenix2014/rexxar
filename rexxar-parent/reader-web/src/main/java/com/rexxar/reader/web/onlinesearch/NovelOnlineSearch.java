package com.rexxar.reader.web.onlinesearch;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;


public interface NovelOnlineSearch {
	
	/**
	 * search books in novel web
	 * @return 所有相关的小说名称
	 * @throws UnsupportedEncodingException 
	 */
	HashMap<String, String> searchNovel(String bookname) throws UnsupportedEncodingException;
	
}
