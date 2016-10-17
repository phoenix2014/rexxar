package com.rexxar.reader.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rexxar.reader.web.component.RetMsg;
import com.rexxar.reader.web.component.Rsps;
import com.rexxar.reader.web.component.Rsps.Loader;
import com.rexxar.reader.web.service.BookshelfService;;


@Controller
@RequestMapping(value = "/bookshelf")
public class BookshelfManagerController {
	
	@Autowired
	private BookshelfService bookshelfService;
	
	@ResponseBody
	@RequestMapping(value = "/get")
	public RetMsg getBooks(final Long userId){
		return Rsps.onResult(new Loader() {
			
			@Override
			public Object load() throws Exception {
				// TODO Auto-generated method stub
				return bookshelfService.getBooks(userId);			
			}
		});
	}
	
	@ResponseBody
	@RequestMapping(value = "/add")
	public RetMsg addBook(final Long userId, final String bookUrl){
		return Rsps.onResult(new Loader() {
			public Object load() throws IOException {
				bookshelfService.addBook(userId, bookUrl);
				return null;
			}
		});
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public RetMsg deleteBook(final Long userId, final Long bookId){
		return Rsps.onResult(new Loader() {
			public Object load() {
				bookshelfService.deleteBook(userId, bookId);
				return null;
			}
		});
	}
	
	@ResponseBody
	@RequestMapping(value = "/search")
	public RetMsg searchBook(final Long userId, final String bookName){
		return Rsps.onResult(new Loader() {
			public Object load() throws UnsupportedEncodingException {
				return bookshelfService.searchBook(userId, bookName);
			}
		});
	}
	
}
