package com.automation.tests.utilities;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tests.automation.FirstScript;


public class Log4jUtility {
	static Logger Logger=LogManager.getLogger(Log4jUtility.class.getName());
	private Logger log=null;
	private static Log4jUtility ob=null;
	
	
	private Log4jUtility() {
		
	}
	
	public static Log4jUtility getInstance() {
		if(ob==null) {
			ob=new Log4jUtility();
		}
		return ob;
	}
	
	public Logger getLogger() {
		if(log==null)
		log=LogManager.getLogger(Log4jUtility.class);
		
		return log;
	}
  
	
}
