package com.rexxar.common.global;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.google.common.base.Splitter;
import com.google.common.base.Throwables;
import com.rexxar.common.utils.ResourceUtil;

/**
 *  
 */
public class Configs {

  public  static final String CONFIG_LOCATION = "config/app.properties";
    
  private static  Properties config = new Properties();
  
  static {
    try {
      ResourceUtil.load(config, CONFIG_LOCATION);
    } catch (IOException e) {
      Throwables.propagate(e);
    }
  }
  
  public static  String getString(String key){
    return config.getProperty(key);
  }
  
  
  public static List<String> getStrList(String key){
    String value =  config.getProperty(key);
    return Splitter.on(",").splitToList(value);
  }

  public static  Integer getInt(String key){
      String value =  config.getProperty(key);
      return Integer.parseInt(value);
  }

  public static   Long   getLong(String key){
    String value =  config.getProperty(key);
    return Long.parseLong(value);
  }
  
  
}

