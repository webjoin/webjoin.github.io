package com.viewt.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
	static Logger l = LoggerFactory.getLogger(LogUtil.class);
	public static void debug(String msg){
		l.debug(msg);
	}public static void error(String msg,Exception e){
		l.error(msg,e);
	}

}
