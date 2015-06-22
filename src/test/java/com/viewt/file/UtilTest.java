package com.viewt.file;

public class UtilTest {
	
	public static void main(String[] args) {
		testMilesecond();
		testnanosecond();
	}
	public static void testMilesecond(){
		System.out.println(System.currentTimeMillis());
	}
	public static void testnanosecond(){
		//十亿分之一秒
		System.out.println(System.nanoTime());
	}
	

}
