package com.rexxar.reader.web.service;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.rexxar.dao.NovelDirectoryMapper;
import com.rexxar.dao.model.NovelDirectoryWithBLOBs;

@Component
public class BookService {

	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	
	private String crawleFiledir = "/home/rexxar/novelstorage/crawledNovel";
	
	//private String crawleFiledir = "/Users/phoenix/Documents/crawledNovel";
	
	@Autowired
	private NovelDirectoryMapper novelDirectoryMapper;
	
	public String getMenu(Long userId,Long bookId){
		
		NovelDirectoryWithBLOBs novelDirectory  = novelDirectoryMapper.selectByPrimaryKey(bookId);
		if (novelDirectory == null) {
			logger.warn("user [{}] select book [{}] menu find no result!!",userId,bookId);
			return null;
		} else {
			String novelMenu = novelDirectory.getText();
			logger.info("user [{}] select book [{}] menu [{}] success",userId,bookId,novelMenu);
			return novelMenu;
		}		
	}
	
	public String[] getChapterContent(Long userId,Long bookId,String chapterName) throws IOException{
		
		String pathName = crawleFiledir + File.separatorChar + bookId.toString() + File.separatorChar + 
				generatorNovelContentFilename(chapterName);
		
		File contentFile = new File(pathName);
		
		List<String> novelContent = Files.readLines(contentFile, Charsets.UTF_8);
		
		String allNovelContent = novelContent.toString().replaceAll("]","").replace("[", "").replaceAll(" +", " ");
		
		String[] returnNovelContent = allNovelContent.split(" ");
		
		logger.info("user [{}] select book [{}] chapter [{} success,  content [{}]",userId,bookId,chapterName,novelContent.toString());
		
		return returnNovelContent;
	}
	
	private String generatorNovelContentFilename(String chaptername)
	{
		checkNotNull(chaptername);
		return Hashing.md5().hashString(chaptername + "phoenix",Charset.forName("utf8")).toString();
	}
	
}
