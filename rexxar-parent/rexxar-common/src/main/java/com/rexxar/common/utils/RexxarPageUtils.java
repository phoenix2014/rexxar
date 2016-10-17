package com.rexxar.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 对抓取网页做特定处理
 * @author phoenix
 *
 */
public class RexxarPageUtils {

    /**
     * 解析html body获取charset，采用httpclient提供的getContentType等方法获取不到
     * @param html
     * @param charset
     * @return 默认返回 UTF-8
     */
    public static String getCharSetByBody(String html){    
    	String charset = "UTF-8";
    	Document document = Jsoup.parse(html);
        Elements elements = document.select("meta");    
        for(Element metaElement : elements){    
            if(metaElement!=null && StringUtils.isNotBlank(metaElement.attr("http-equiv")) && metaElement.attr("http-equiv").toLowerCase().equals("content-type")){    
                String content = metaElement.attr("content");    
                charset = getCharSet(content);    
                break;    
            }
        }
        return charset;    
    }
        
    /**  
     * 正则获取字符编码  
     * @param content  
     * @return  
     */    
    private static String getCharSet(String content){    
        String regex = ".*charset=([^;]*).*";    
        Pattern pattern = Pattern.compile(regex);    
        Matcher matcher = pattern.matcher(content);    
        if(matcher.find())    
            return matcher.group(1);    
        else    
            return null;    
    }  
    
    /**
     * 字符串编码转换的实现方法
     * @param str  待转换编码的字符串
     * @param newCharset 目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException {
	     if (str != null) {
		      //用默认字符编码解码字符串。
		      byte[] bs = str.getBytes();
		      //用新的字符编码生成字符串
		      return new String(bs, newCharset);
	     }
	     return null;
    }
    
    /**
     * 字符串编码转换的实现方法
     * @param str  待转换编码的字符串
     * @param oldCharset 原编码
     * @param newCharset 目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String changeCharset(String str, String oldCharset, String newCharset) throws UnsupportedEncodingException {
     if (str != null) {
	      //用旧的字符编码解码字符串。解码可能会出现异常。
	      byte[] bs = str.getBytes(oldCharset);
	      //用新的字符编码生成字符串
	      return new String(bs, newCharset);
     }
     
     return null;
    }
}
