package com.rexxar.reader.web.boot;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import com.google.common.collect.Lists;
import com.rexxar.common.global.Configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RexxarReaderWebBoot {
	private static final Logger logger = LoggerFactory.getLogger(RexxarReaderWebBoot.class);
	
	public static void main(String[] args) {
	    logger.info("Starting server....");
	    
	    try {
	      startJettyServer();
	    } catch (Exception e) {
	      logger.error(e.getMessage(),e);
	      e.printStackTrace();
	    }
	    
	    logger.info("Server Shutdown");

	}

	private static void startJettyServer() throws Exception {

		final Server server = new Server(Configs.getInt("jetty.port"));
		
		WebAppContext context = new WebAppContext();
		String classesPath = RexxarReaderWebBoot.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm();
		String webContextPath = StringUtils.substringBefore(classesPath, "/WEB-INF");
		logger.info("classesPath : {}",classesPath);
		logger.info("web context : {}",webContextPath);
		
		context.setResourceBase(webContextPath);
		context.setContextPath("/");
		context.setParentLoaderPriority(true);
		context.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
		context.setClassLoader(Thread.currentThread().getContextClassLoader());
	    context.setConfigurationClasses(Lists.newArrayList("org.eclipse.jetty.webapp.WebInfConfiguration",
	            "org.eclipse.jetty.webapp.WebXmlConfiguration",
	            "org.eclipse.jetty.webapp.MetaInfConfiguration",
	            "org.eclipse.jetty.webapp.FragmentConfiguration",
	            "org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
	            "org.eclipse.jetty.annotations.AnnotationConfiguration"));
	   
	    server.setHandler(context);

	    server.start();		
	    
	    logger.info("reader server start");
	    
	    Runtime.getRuntime().addShutdownHook(new Thread() {
	        @Override
	        public void run() {
	          try {
	            server.stop();
	            logger.info("reader server stop");
	          } catch (Exception e) {
	            logger.error(e.getMessage(),e);
	            e.printStackTrace();
	          }
	        }
	      });
	      
	    server.join();
	    
	}
}
