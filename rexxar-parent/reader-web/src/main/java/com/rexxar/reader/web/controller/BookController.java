package com.rexxar.reader.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rexxar.reader.web.component.RetMsg;
import com.rexxar.reader.web.component.Rsps;
import com.rexxar.reader.web.component.Rsps.Loader;
import com.rexxar.reader.web.service.BookService;

@Controller
@RequestMapping(value = "/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@ResponseBody
	@RequestMapping(value = "/getMenu")
	public RetMsg getMenu(final Long userId, final Long bookId)
	{
		return Rsps.onResult(new Loader() {
			
			@Override
			public Object load() throws Exception {
				// TODO Auto-generated method stub
				return bookService.getMenu(userId, bookId);
			}
		});
	}
	
	@ResponseBody
	@RequestMapping(value = "/getChapterContent")
	public RetMsg getChapterContent(final Long userId, final Long bookId, final String chapterName)
	{
		return Rsps.onResult(new Loader() {
			
			@Override
			public Object load() throws Exception {
				// TODO Auto-generated method stub
				return bookService.getChapterContent(userId, bookId, chapterName);
			}
		});
	}
}