package com.rexxar.common.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.hash.Hashing;

import static com.google.common.base.Preconditions.checkNotNull;

import java.nio.charset.Charset;


public class RexxarNovelUtils {
	
	private static Logger logger = LoggerFactory.getLogger(RexxarNovelUtils.class);

	/**
	 * 生成小说唯一id，16位的bigint，  随机生成的三位数+时间戳倒序
	 * @return
	 */
	public static Long generatorNovelId()
	{
		String threeRandomSting =  RandomStringUtils.randomNumeric(3);
		String timeStamp = String.valueOf(System.currentTimeMillis());
		String idString = StringUtils.reverse(timeStamp) + threeRandomSting;
		
		return NumberUtils.toLong(idString);
	}
	
	public static String generatorNovelContentFilename(String chaptername)
	{
		checkNotNull(chaptername);
		return Hashing.md5().hashString(chaptername + "phoenix",Charset.forName("utf8")).toString();
	}
	
	public static void main(String[] args)
	{
		for (int i = 0; i < 10; i++) {
			logger.debug("{}",generatorNovelId());
			logger.debug("{}",generatorNovelContentFilename("第二章 大杀四方"));
		}		
	}
}
