package com.rexxar.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;

import com.rexxar.common.global.Configs;


/**
 * httpclient工具类
 * 
 * @author phoenix
 * @version 
 */
public class HttpClientUtils {
	
	private static final Logger Log = LoggerFactory.getLogger(HttpClientUtils.class);
	private static PoolingHttpClientConnectionManager poolingmgr;
  
	private static final String HTTPCLIENT_CONNECTTIMEOUT = "httpclient.connectTimeout";
	private static final String HTTPCLIENT_SOCKETTIMEOUT  = "httpclient.socketTimeout";
	private static final String HTTPCLIENT_AGENT          = "httpclient.agent";
	//private static final String HTTPCLIENT_CONNMANAGERTIMEOUT          = "httpclient.connManagerTimeout";
	private static final String HTTPCLIENT_MAXTOTAL          = "httpclient.maxTotal";
	private static final String HTTPCLIENT_MAXPERROUTE         = "httpclient.maxPerRoute";
	private static final String HTTPCLIENT_RETRYCOUNT         = "httpclient.retryCount";
  
	private static HttpClient   httpClient;
	
	static {
	    Integer connectTimeOut = Configs.getInt(HTTPCLIENT_CONNECTTIMEOUT);
	    Integer socketTimeOut =  Configs.getInt(HTTPCLIENT_SOCKETTIMEOUT);
	    //Integer connManagerTimeOut =  Configs.getInt(HTTPCLIENT_CONNMANAGERTIMEOUT);
	    String  agentStr      = Configs.getString(HTTPCLIENT_AGENT);	    
	    Integer maxTotal =  Configs.getInt(HTTPCLIENT_MAXTOTAL);
	    Integer maxPerRoute =  Configs.getInt(HTTPCLIENT_MAXPERROUTE);
	    Integer retryCount = Configs.getInt(HTTPCLIENT_RETRYCOUNT);
	    
	    RequestConfig requestConfig =
	        RequestConfig.custom().setConnectTimeout(connectTimeOut).setSocketTimeout(socketTimeOut).build();
	    
	    DefaultHttpRequestRetryHandler dhr = new DefaultHttpRequestRetryHandler(retryCount,true);
	    
	    poolingmgr = new PoolingHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create()
	            .register("http", PlainConnectionSocketFactory.getSocketFactory()).build());
	    poolingmgr.setDefaultMaxPerRoute(maxPerRoute);
	    poolingmgr.setMaxTotal(maxTotal);
	    
	    httpClient =
	            HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).setUserAgent(agentStr)
	            .setConnectionManager(poolingmgr)
	            .setConnectionReuseStrategy(new NoConnectionReuseStrategy()).setRetryHandler(dhr).build();
	    }
	
	  /**
	   * get method获取字符串响应
	   * 
	   * @param request
	   * @return
	   */
	  public static String getContent(HttpUriRequest request) {
		  ResponseHandler<String> responseHandler = new BasicResponseHandlerEx();
		  String content = null;
		  try {
		  	content = httpClient.execute(request, responseHandler);
		  } catch (IOException e) {
		  	Log.error(request.getRequestLine().toString(), e);
		  	request.abort();
		  } catch (Exception e) {
		  	Log.error("Unkown Exception" + e.getMessage(),e);
		  	Throwables.propagate(e);
		  }
		  return content;
	  }
	  
	  /**
	   * get method获取字符串响应
	   * 
	   * @param url
	   * @return
	   */
	  public static String getContent(String url){
	    HttpGet httpGet = new HttpGet(url);
	    return getContent(httpGet);
	  }
	  
	  /**
	   * post 获取 字符串响应
	   * 
	   * @param url
	   * @param params
	   * @return
	   */
	  public static String post(String url,Map<String, String> params){
	    List<NameValuePair>   postParameters = new ArrayList<NameValuePair>();
	    if (!params.isEmpty()) {
	      for (Map.Entry<String, String> entry : params.entrySet()) {
	        postParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
	      }
	    }
	    HttpPost post = new HttpPost(url);
	    try {
	      post.setEntity(new UrlEncodedFormEntity(postParameters));
	    } catch (UnsupportedEncodingException e) {
	      Throwables.propagate(e);
	    }
	    return getContent(post);
	  }

}
