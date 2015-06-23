package com.viewt.gb;

import java.sql.Connection;

public class ThreadIdGenerator {
	

    private static final ThreadLocal < Connection > unique = 
        new ThreadLocal < Connection > () {
            @Override 
            protected Connection initialValue() {
//                try {
//					return DBUtil.openConnection();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
                return null ;
        }
    };

    public static Connection get() {
        return unique.get();
    }
    
    public static void set(Connection conn) {
        unique.set(conn);
    }

}
