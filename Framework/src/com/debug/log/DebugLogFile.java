package com.debug.log;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.autoframe.lib.DataStore;


public class DebugLogFile {
	
	public  static Logger logger = Logger.getLogger(DataStore.D_DebugLogger);
	public  static FileHandler fileHandler=null;
	public static String path=DataStore.D_LogPath+File.separator+"Debug.log";
	
	public static void type(String p_info){	
		try {
			
			fileHandler = new FileHandler(path,true);
			fileHandler.setFormatter(new SimpleFormatter());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.severe(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	logger.addHandler(fileHandler);
    	logger.info(p_info);
    	fileHandler.close();
		
	}

}
