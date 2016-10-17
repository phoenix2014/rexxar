package com.rexxar.reader.web.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.rexxar.dao.NovelOverviewMapper;
import com.rexxar.dao.ReaderBookshelfMapper;
import com.rexxar.dao.model.NovelOverview;
import com.rexxar.dao.model.NovelOverviewExample;
import com.rexxar.dao.model.ReaderBookshelf;
import com.rexxar.dao.model.ReaderBookshelfExample;
import com.rexxar.dao.model.ReaderBookshelfExample.Criteria;
import com.rexxar.reader.web.component.ResCode;
import com.rexxar.reader.web.crawler.NewNovelCrawler;
import com.rexxar.reader.web.exception.HttpApiServiceException;
import com.rexxar.reader.web.onlinesearch.BqgNovelOnlineSearch;


@Component
public class BookshelfService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookshelfService.class);

	@Autowired
	private ReaderBookshelfMapper readerBookshelfMapper;
	
	@Autowired
	private NovelOverviewMapper novelOverviewMapper;
	
	@Autowired
	private NewNovelCrawler newNovelCrawler;
	
	public ImmutableList<HashMap<String, String>> getBooks(Long userId){
		
		ReaderBookshelfExample readerBookshelfExample = new ReaderBookshelfExample();
		Criteria criteria = readerBookshelfExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		ArrayList<ReaderBookshelf>  readerBookshelfList = (ArrayList<ReaderBookshelf>) readerBookshelfMapper.selectByExample(readerBookshelfExample);
		if (readerBookshelfList.size() == 0) {
			logger.info("get user[{}] books null",userId);
			return null;
		} else {
			ArrayList<HashMap<String, String>> novelinfos = Lists.newArrayList();
			
			for (int i = 0; i < readerBookshelfList.size(); i++) {
				HashMap<String, String> novelinfo = new HashMap<String, String>();
				novelinfo.put("bookName", readerBookshelfList.get(i).getNovelName());
				novelinfo.put("bookId", readerBookshelfList.get(i).getNovelId().toString());
				novelinfo.put("bookImg", readerBookshelfList.get(i).getImgUrl());
				novelinfo.put("readProgress", readerBookshelfList.get(i).getReaderProgress().toString() + '%');
				
				novelinfos.add(novelinfo);
			}
			
			logger.info("get user[{}] books [{}] ",userId, novelinfos.toString());
			return ImmutableList.copyOf(novelinfos);
		}
	}
	
	/**
	 * 用户点击添加小说按钮时，第一步创建小说抓取任务，第二部将小说加入用户的书架
	 * @param userId
	 * @param bookUrl
	 * @throws IOException
	 */
	public void addBook(Long userId, String bookUrl) throws IOException{
		//第一步创建小说抓取任务
		Long novelId = newNovelCrawler.CrawleNewNovel(userId,bookUrl);
		
		//第二部将小说加入用户的书架
		addBookToDB(userId, novelId);		
	}
	
	
	public void addBookToDB(Long userId, Long bookId){
		
		NovelOverview novelOverview = novelOverviewMapper.selectByPrimaryKey(bookId);
		try{
			Preconditions.checkNotNull(novelOverview);
		}catch(Exception e) {
			throw new HttpApiServiceException(ResCode.NOVEL_NOT_EXIST_ERROR.errno,
					ResCode.NOVEL_NOT_EXIST_ERROR.errmsg);
		}
		
		ReaderBookshelfExample readerBookshelfExample = new ReaderBookshelfExample();
		Criteria criteria = readerBookshelfExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andNovelIdEqualTo(bookId);
		ArrayList<ReaderBookshelf>  readerBookshelfList = (ArrayList<ReaderBookshelf>) readerBookshelfMapper.selectByExample(readerBookshelfExample);
		
		logger.info("search readerBookshelf : {}",readerBookshelfList);
		
		try{
			Preconditions.checkArgument(readerBookshelfList.size() == 0);
		}catch(Exception e) {
			throw new HttpApiServiceException(ResCode.NOVEL_ALREADY_EXIST_ERROR.errno,
					ResCode.NOVEL_ALREADY_EXIST_ERROR.errmsg);
		}
		
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		
		ReaderBookshelf readerBookshelf = new ReaderBookshelf();
		readerBookshelf.setNovelId(bookId);
		readerBookshelf.setNovelName(novelOverview.getBookname());
		readerBookshelf.setImgUrl(novelOverview.getImgUrl());
		readerBookshelf.setUserId(userId);
		readerBookshelf.setReaderProgress(new BigDecimal(0.00));
		readerBookshelf.setUpdateTime(timeStamp);
		readerBookshelf.setCreateTime(timeStamp);
		
		readerBookshelfMapper.insert(readerBookshelf);
		
		logger.info("add user[{}] book[{}] success!",userId,bookId);
		
	}
	
	public void deleteBook(Long userId, Long bookId){
		ReaderBookshelfExample readerBookshelfExample = new ReaderBookshelfExample();
		Criteria criteria = readerBookshelfExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andNovelIdEqualTo(bookId);
		int resultLines = readerBookshelfMapper.deleteByExample(readerBookshelfExample);
		
		if (resultLines > 0) {
			logger.info("delete user[{}] book[{}] success",userId,bookId);
		} else {
			logger.info("delete user[{}] book[{}] fail",userId,bookId);
		}
	}
	
	
	/**
	 * 
	 * @param UserId
	 * @param bookName
	 * @return  maps<bookname,bookid> , if find no result ,return null;
	 * @throws UnsupportedEncodingException 
	 */
	public ImmutableMap<String, String> searchBook(Long userId, String bookName) throws UnsupportedEncodingException{
		
		BqgNovelOnlineSearch bqgNovelOnlineSearch = new BqgNovelOnlineSearch();
		logger.info("user {} search {}",userId,bookName);
		
		return ImmutableMap.copyOf(bqgNovelOnlineSearch.searchNovel(bookName));	
	}	

	/**
	 * 
	 * @param UserId
	 * @param bookName
	 * @return  maps<bookname,bookid> , if find no result ,return null;
	 */
	public ImmutableMap<String, Long> searchBookOld(Long userId, String bookName){
		
		NovelOverviewExample novelOverviewExample = new NovelOverviewExample();
		com.rexxar.dao.model.NovelOverviewExample.Criteria criteria  = novelOverviewExample.createCriteria();
		criteria.andBooknameBlurryLike(bookName);
		
		ArrayList<NovelOverview> likeBookNameList = (ArrayList<NovelOverview>) novelOverviewMapper.selectByExample(novelOverviewExample);
		if (likeBookNameList.size() == 0) {
			logger.info("user[{}] search book[{}] find no result!",userId,bookName);
			return null;
		} else {
			HashMap<String, Long> bookinfo = new HashMap<>();
			for (int i = 0; i < likeBookNameList.size(); i++) {
				bookinfo.put(likeBookNameList.get(i).getBookname(), likeBookNameList.get(i).getId());
			}
			
			return ImmutableMap.copyOf(bookinfo);
		}
		
	}
	
}
