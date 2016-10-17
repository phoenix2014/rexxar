package com.rexxar.reader.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rexxar.reader.web.component.RetMsg;
import com.rexxar.reader.web.component.Rsps;
import com.rexxar.reader.web.component.Rsps.Loader;
import com.rexxar.reader.web.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class ReaderUserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/login")
	public RetMsg login(final short os, final String UUID){
		return Rsps.onResult(new Loader() {
			
			@Override
			public Object load() throws Exception {
				// TODO Auto-generated method stub
				return userService.login(os, UUID);
			}
		});
	}
		
}
