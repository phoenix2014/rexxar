package com.rexxar.reader.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.sql.Timestamp;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rexxar.dao.ReaderUserMapper;
import com.rexxar.dao.model.ReaderUser;
import com.rexxar.dao.model.ReaderUserExample;
import com.rexxar.dao.model.ReaderUserExample.Criteria;
import com.rexxar.reader.web.component.UserUtil;


@Component
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private ReaderUserMapper readerUserMapper;
	
	public HashMap<String, String> login(short os, String UUID){
		
		HashMap<String, String> userinfo = new HashMap<>();
		
		ReaderUserExample readerUserExample = new ReaderUserExample();
		Criteria criteria = readerUserExample.createCriteria();
		criteria.andUuidEqualTo(UUID);
		ArrayList<ReaderUser>  readerUserList = (ArrayList<ReaderUser>) readerUserMapper.selectByExample(readerUserExample);
		if (readerUserList.size() == 0) {
			logger.info("uuid [%s] not register, register new user for login",UUID);
			Pair<Long, String> useridinfo = register(os, UUID);
			userinfo.put("userId", useridinfo.getLeft().toString());  // a little trick, no app to convert string to long
			userinfo.put("userName", useridinfo.getRight());
		}else{
			logger.info("uuid [%s] already register, allow login",UUID);
			userinfo.put("userId", readerUserList.get(0).getUserId().toString() );
			userinfo.put("userName",readerUserList.get(0).getUserName() );			
		}
		
		return userinfo;
	} 
	
	private Pair<Long, String> register(short os,String UUID){
		
		Long userId = UserUtil.generatorUserId();
		String userName = "游客" + userId.toString();
		
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		
		ReaderUser readerUser = new ReaderUser();
		readerUser.setOs(os);
		readerUser.setUserId(userId);
		readerUser.setUserName(userName);
		readerUser.setUuid(UUID);
		readerUser.setUpdateTime(timeStamp);
		readerUser.setCreateTime(timeStamp);
		
		int result = readerUserMapper.insert(readerUser);
		if (result == 1) {
			return Pair.of(userId, userName);
		}else{
			logger.error("os[%d] uuid[%s] register error!!", os,UUID);
			return Pair.of(0L, "游客");
		}
	}
	
}
