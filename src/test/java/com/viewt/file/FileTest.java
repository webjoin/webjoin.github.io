package com.viewt.file;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileTest {
	
	public static void main(String[] args) {
		String propertyFile = "/Users/Elijah/.RedisClient.properties";
		File file = new File(propertyFile);
		try {
			file.createNewFile();
			System.out.println(file.exists());
			FileReader fr = new FileReader(file);
			System.out.println(fr.read());
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
