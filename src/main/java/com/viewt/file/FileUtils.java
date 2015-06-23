package com.viewt.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.viewt.log.LogUtil;

public class FileUtils {
	
	
	public static String[] getSubdirs(String dir){
		return getSubdirsOrfiles(new File(dir),QueryFileType.One) ;
	}
	
	public static String[] getSubfiles(String dir){
		return getSubdirsOrfiles(new File(dir),QueryFileType.Two) ;
	}
	public static String[] getSubfilesAndFiles(String dir){
		return getSubdirsOrfiles(new File(dir),QueryFileType.Three) ;
	}
	
	/**
	 *@author Elijah
	 **/
	public static String[] getSubdirsOrfiles(File dir,QueryFileType type){
		List<String> list = new ArrayList<String>();
		if(!dir.exists()){
			LogUtil.debug(dir.getAbsolutePath()+"--file is not exsis");
		}
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			switch(type){
			case One:
				if(files[i].isDirectory())list.add(files[i].getName());
				break;
			case Two:
				if(files[i].isFile())list.add(files[i].getName());
				break;
			case Three:
				if(files[i].isDirectory() || files[i].isFile() )list.add(files[i].getName());
				break;
			}
			
		}
		return list.toArray(new String[list.size()]) ;
	}
	

	public static enum QueryFileType{
		One,Two,Three
	}
}
