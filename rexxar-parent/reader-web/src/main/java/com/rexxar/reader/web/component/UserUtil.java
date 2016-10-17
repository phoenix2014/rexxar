package com.rexxar.reader.web.component;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(UserUtil.class);
	
	public static Long generatorUserId(){
		String threeRandomSting =  RandomStringUtils.randomNumeric(3);
		String timeStamp = String.valueOf(System.currentTimeMillis());
		String idString = StringUtils.reverse(timeStamp) + threeRandomSting;
		
		return NumberUtils.toLong(idString);
	}
	
}
