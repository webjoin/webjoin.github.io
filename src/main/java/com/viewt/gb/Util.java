package com.viewt.gb;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class Util {
	
	public final static String gbFile = System.getProperty("user.home")+ "/.gb.properties";
	public final static String zhFile = Util.class.getResource("").getPath()+"zh.properties";
	public final static String enFile = Util.class.getResource("").getPath()+"en.properties";
	public final static Properties zhProps = new Properties();
	public final static Properties enProps = new Properties();
	
	private static DataSource ds = null;   
    public final static Properties dbProps = new Properties();
    public  static void initProperties(){
    	try{
    		dbProps.clear();
            InputStream in = new FileInputStream(gbFile);
            dbProps.load(in);
            in.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public  static void initDB() throws Exception{
			initProperties();
			ds = DruidDataSourceFactory.createDataSource(dbProps);
    }
     
    public static Connection getConnection() throws SQLException{
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
			conn = Util.getConnection();
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
    	dbProps.put(name, value);
    }
    public static String getProperty(String name){
    	return (String)dbProps.get(name);
    }

    
    
	/**
	 * 
	 * 复制
	 * 
	 */
	public static void copy(String fromFile, String toFile) throws IOException {
		FileInputStream inputStream = new FileInputStream(fromFile);
		FileChannel fromChannel = inputStream.getChannel();

		FileOutputStream outputStream = new FileOutputStream(toFile);
		FileChannel toChannel = outputStream.getChannel();

		toChannel.transferFrom(fromChannel, 0, fromChannel.size());
		// fromChannel.transferTo(0, fromChannel.size(), toChannel);
		toChannel.force(true);
		inputStream.close();
		fromChannel.close();
		outputStream.close();
		toChannel.close();
	}
}
