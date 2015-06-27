package com.viewt.gb;

import java.util.Properties;

public class I1n8ThreadLocal {
	
	private static ThreadLocal<Properties>  th = new ThreadLocal<Properties>(){

		@Override
		protected Properties initialValue() {
			return Util.zhProps;
		}
	};
	
	public static Properties get(){
		return th.get();
	}
	
	public static void set(Properties pro){
		th.set(pro);
	}

}
