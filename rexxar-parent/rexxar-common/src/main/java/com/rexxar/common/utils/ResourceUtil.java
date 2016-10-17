package com.rexxar.common.utils;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;

import com.google.common.io.Resources;

public class ResourceUtil {
  public static void load(Properties properties,String fileClassPath) throws IOException{
    loadFromFilePath(properties, Resources.getResource(fileClassPath));
    
  }
  
  public static void loadFromFilePath(Properties properties,URL url) throws IOException{
    String str = Resources.toString(url, Charset.forName("utf-8"));
    properties.load(new StringReader(str));
  }

}
