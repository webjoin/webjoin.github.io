package com.viewt.btrace;

import java.util.Random;

/**
 * @author Elijah
 * Ö´ÐÐÃüÁî 
 * C:\Users\9client\Downloads\btrace-bin\bin\btrace 12820 D:\workspace-xmpp\xmpp-bosh\src\com\nine\btrace\TraceHelloWor
ld.java
 */
public class Helloworld {
	
	
	 public static void main(String[] args) throws Exception {  
	        while (true) {  
	            Random random = new Random();  
	            execute(random.nextInt(4000));  
	        }  
	    }  
	    public static Integer execute(int sleepTime) {  
	        try {  
	            Thread.sleep(sleepTime);  
	        } catch (Exception e) {
	        }  
	        System.out.println("sleep time is=>"+sleepTime);  
	        return 0;  
	    }  
}
