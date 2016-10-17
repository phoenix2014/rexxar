/**
 * 小说抽取
 */
package com.rexxar.common.extract;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.nodes.Document;


/**
 * @author phoenix
 * 小说抽取的通用接口
 */
public interface NovelExtract {
	/**
	 * 抽取小说名称
	 * @return
	 */
	String getName(Document novelHtml);  
	
	/**
	 * 抽取小说作者
	 * @return
	 */
	String getAuthor(Document novelHtml);  
	
	/**
	 * 抽取小说封面
	 * @return
	 */
	String getImgUrl(Document novelHtml);  
	
	/**
	 * 抽取小说简介
	 * @return
	 */
	String getDescribe(Document novelHtml);  
	
	/**
	 * 抽取小说类别
	 * @return
	 */
	String getCagetory(Document novelHtml); 
	
	/**
	 * 抽取小说最新章节名称
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	String getLatestChapterName(Document novelHtml) throws UnsupportedEncodingException;  
	
	/**
	 * 抽取小说状态
	 * @return
	 */
	String getStatus(Document novelHtml);  
	
	/**
	 * 获取小说所有章节
	 * @param novelHtml
	 * @return
	 */
	String[] getALLDirectory(Document novelHtml);
	
	/**
	 * 获取小说所有章节和下载地址
	 * @param novelHtml,prexUrl
	 * @return <dirs,urls>
	 */
	Pair<String[],String[]>  getDirsAndUrls(Document novelHtml,String prexUrl);
	
	/**
	 * 获取小说章节总数，超过 GlobalConstant.DIRECTORYMAXNUM 不抓取
	 * @param novelHtml
	 * @return
	 */
	short getDirectoryCount(Document novelHtml);
	
	/**
	 * 获取小说章节里面的内容
	 * @param novelContentHtml
	 * @return
	 */
	String getNovelChapterContent(Document novelContentHtml);
	
	/**
	 * 获取小说最近章节更新时间
	 * @param novelContentHtml
	 * @return
	 */
	String getLatestNovelUpdateTime(Document novelHtml);
	
	/**
	 * 小说是否已经完结
	 * @param novelHtml
	 * @return
	 * @throws ParseException 
	 */
	boolean isNovelEnd(Document novelHtml) throws ParseException;
	
}
