package com.viewt.gb.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import com.viewt.file.FileUtils.QueryFileType;
import com.viewt.log.LogUtil;

public class FileBean {
	private String parent ;
	
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public FileBean() {}
	
	public String toString(){
//		{name:"",childran:[{name:"",childran[]}]}
		return null ;
	}
	private FileBean[] subDirs = null ;
	private String name;
	// private
	private List<FileBean> valueBeans = new ArrayList<FileBean>();

	/**
	 *@param parentBean 
	 *			parent一定要有
	 *			name 一定要有 
	 */
	private File[] getFile(FileBean parentBean) {
		File[] files = null;
		File parentFile = new File(parentBean.parent);
		if (parentFile.isDirectory()) {
			files = parentFile.listFiles();
			if (files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					FileBean parentBean1 = new FileBean();
					parentBean1.parent = parentBean.getName();
					parentBean1.name = files[i].getAbsolutePath();
					getFile(parentBean1);
				}
			}
		}
		FileBean fileBean = new FileBean();
		fileBean.name = parentFile.getName();

		if(files != null && files.length > 0){
			for (int i = 0; i < files.length; i++) {
				FileBean fileBean1 = new FileBean();
				fileBean1.name = files[i].getName();
//				fileBean.addSubDir(fileBean1);
			}
			valueBeans.add(fileBean);
//			System.out.println("parent"+parentFile.getName()+",subDir:"+nameStr);
		}else{
			System.out.println("parent"+parentFile.getName()+",subDir:0");
		}
		return files;
	}
	
	
	public FileBean[] getFile1(String path) throws Exception {
		FileBean[] fbs = null ;
		File file = new File(path);
		if(file.exists()){
			if(file.isDirectory()){
				fbs = new FileBean[file.list().length];
				File [] files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					fbs[i] = new FileBean();
					fbs[i].name = files[i].getAbsolutePath();
//					fbs[i].parent = parentPath;
					
					if(files[i].isDirectory())
						fbs[i].subDirs = getFile1(fbs[i].name);
					if(files[i].isFile()) //如果是文件了则子文件夹为空
						fbs[i].subDirs = new FileBean[0]; 
				}
			}else if(file.isFile()){
				fbs = new FileBean[1];
				fbs[0] = new FileBean();
				fbs[0].name = path;
//				fbs[0].parent = parentPath;
				fbs[0].subDirs = new FileBean[0]; 
			}
//			FileBean bean = new FileBean();
//			bean.name = parentPath ;
//			bean.parent = "";
//			bean.subDirs = fbs;
			return fbs;
		}else{
			throw new IOException("the file  not found , pls enter correct filename");
		}
//		return null ;
	}

	public static void main(String[] args) throws Exception{
		String dir = "/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt";
//		dir = "/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb/file";
		FileBean bean = new FileBean();
		FileBean[] beann = bean.getFile1(dir);
		bean.name = dir;
		bean.subDirs = beann ;
		JSONObject obj = new JSONObject(bean);
		System.out.println(obj.toString());
		
	}

	public String[] getSubdirs(String dir) {
		return getSubdirsOrfiles(new File(dir), QueryFileType.One);
	}

	public String[] getSubfiles(String dir) {
		return getSubdirsOrfiles(new File(dir), QueryFileType.Two);
	}

	public String[] getSubfilesAndFiles(String dir) {
		return getSubdirsOrfiles(new File(dir), QueryFileType.Three);
	}

	/**
	 * @author Elijah
	 **/
	public String[] getSubdirsOrfiles(File dir, QueryFileType type) {
		List<String> list = new ArrayList<String>();
		if (!dir.exists()) {
			LogUtil.debug(dir.getAbsolutePath() + "--file is not exsis");
		}
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			switch (type) {
			case One:
				if (files[i].isDirectory())
					list.add(files[i].getName());
				break;
			case Two:
				if (files[i].isFile())
					list.add(files[i].getName());
				break;
			case Three:
				if (files[i].isDirectory() || files[i].isFile())
					list.add(files[i].getName());
				break;
			}
		}
		return list.toArray(new String[list.size()]);
	}

	public enum QueryFileType {
		One, Two, Three
	}

	public FileBean[] getSubDirs() {
		return subDirs;
	}

	public void setSubDirs(FileBean[] subDirs) {
		this.subDirs = subDirs;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	
}
