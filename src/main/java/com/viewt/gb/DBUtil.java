package com.viewt.gb;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DBUtil {
	
	
	private static DataSource ds = null;   
    private static Properties props = null ;
    public  static void initProperties(){
    	try{
            InputStream in = DBUtil.class.getResourceAsStream("ds.properties");//getResourceAsStream(DBUtil.class.getResource("").getPath()+"ds.properties");
            props = new Properties();
            props.load(in);
            System.out.println(props.get("url"));
            in.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public  static void initDB(){
		try {
			ds = DruidDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
     
    public static Connection openConnection() throws SQLException{
        return ds.getConnection();
    }
    public static void closeConnection(Connection conn) throws SQLException{
    	conn.close();    	
    }
    
    
    public static void main(String[] args) throws IOException {
//    	 InputStream in =  DBUtil.class.getResourceAsStream("ds.properties");
//    	 Properties p = new Properties();
//    	 p.load(in);
//    	 System.out.println(p.get("url"));
//    	 in.close();
//    	String add = DBUtil.class.getResource("").getPath()+"ds.properties";
//    	System.out.println(add);
//    	File file = new File(add);
//    	System.out.println(file.exists());
    	Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into viewt values('Anna')";
		try {
			conn = DBUtil.openConnection();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null & !ps.isClosed())
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null & !conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    
    
    public static void setProperty(String name,String value){
    	props.put(name, value);
    }
    public static String getProperty(String name){
    	return (String)props.get(name);
    }

}
