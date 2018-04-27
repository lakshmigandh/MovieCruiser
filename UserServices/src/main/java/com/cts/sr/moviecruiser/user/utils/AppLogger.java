package com.cts.sr.moviecruiser.user.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppLogger.class);

	
	public static void info(String arg){
		LOGGER.info(arg);
	}
	
	public static void warn(String arg){
		LOGGER.warn(arg);
	}
	
	public static void error(String arg,Object obj){
		LOGGER.error(arg,obj);
	}
	
}
