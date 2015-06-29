package com.viewt.gb;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class Util {

	public final static String gbFile = System.getProperty("user.home")
			+ "/.gb.properties";
	public final static String zhFile = Util.class.getResource("").getPath()
			+ "zh.properties";
	public final static String enFile = Util.class.getResource("").getPath()
			+ "en.properties";
	public final static Properties zhProps = new Properties();
	public final static Properties enProps = new Properties();

	private static DataSource ds = null;
	public final static Properties dbProps = new Properties();

	public static void initProperties() {
		try {
			dbProps.clear();
			InputStream in = new FileInputStream(gbFile);
			dbProps.load(in);
			in.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void initDB() throws Exception {
		initProperties();
		ds = DruidDataSourceFactory.createDataSource(dbProps);
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	public static void closeConnection(Connection conn) throws SQLException {
		conn.close();
	}

	public static void main(String[] args) throws Exception {
		// InputStream in = DBUtil.class.getResourceAsStream("ds.properties");
		// Properties p = new Properties();
		// p.load(in);
		// System.out.println(p.get("url"));
		// in.close();
		// String add = DBUtil.class.getResource("").getPath()+"ds.properties";
		// System.out.println(add);
		// File file = new File(add);
		// System.out.println(file.exists());
//		Connection conn = null;
//		PreparedStatement ps = null;
//		String sql = "insert into viewt values('Anna')";
//		try {
//			conn = Util.getConnection();
//			ps = conn.prepareStatement(sql);
//			ps.executeUpdate();
//			ps.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (ps != null & !ps.isClosed())
//					ps.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (conn != null & !conn.isClosed())
//					conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		
		String tableName = "ofMucConversationLog" ;
		System.out.println(getJavaName(tableName));
		initDB();
		System.out.println(generateJavaClass(tableName));
	}

	public static void setProperty(String name, String value) {
		dbProps.put(name, value);
	}

	public static String getProperty(String name) {
		return (String) dbProps.get(name);
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

	public static String getJavaName(String tableName){
		tableName = tableName.toLowerCase();
		String reg = "_";
		String firstChar = tableName.substring(0, 1).toUpperCase() ; 
		if(tableName.contains(reg)){
			char[] chars = tableName.substring(1).toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if(chars[i] == '_'){
					//chars[i] = '';
					chars[i+1] = (char)(chars[i+1]-32);
					i++;
				}
			}
			tableName = firstChar+new String(chars).replace(reg, "");
		}else{
			tableName  = firstChar+tableName.substring(1);
		}
//		String lower = tableName.toLowerCase();
//		String [] tbs  = (lower.charAt(0)+lower.substring(1)).split(reg);
//		Pattern pa = Pattern.compile(reg);
//		Matcher ma = pa.matcher(tableName.toLowerCase());
//		String name = "";
//		int i = 0 ;
//		while(ma.find()){
//			name += tbs[i]+ma.group().replace("_", "").toUpperCase();
//			i++;
//		}
		return tableName ;
		
	}
	public static String getFieldType(int type){
//		int type = metaData.getColumnType(i);
		String typeStr = "";
	     //判断
		switch(type){
		case Types.INTEGER :
		case Types.BIGINT :
			return  "Integer";
		case Types.VARCHAR :
		case Types.CHAR  :
		case Types.CLOB  :
		case Types.LONGVARCHAR:
			return "String";
		case Types.DATE  :
			return "Date";
		}
	     
	     return typeStr ;
	}
	/**
	 * 获取所有表结构
	 * select table_name from information_schema.tables where table_schema='openfire'
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static String[] queryTables(String sql) throws SQLException {
		Connection conn = getConnection();
		ResultSet rs =  getRs(conn, sql);
		List<String> ls = new ArrayList<String>();
		while(rs.next()){
			ls.add(rs.getString(1));
		}
		rs.close();
		conn.close();
		return ls.toArray(new String[ls.size()]) ;
	}
	/**
	 * 获取查询所有表的sql
	 * @return
	 */
	public static String getQueryTablesSql(){
		if(isMysql()){
			return "select table_name from information_schema.tables where table_schema='"+dbProps.getProperty("db")+"'";
		}else{
			return "select table_name from user_tables where table_name = '"+dbProps.getProperty("username")+"' ";
		}
		
	}
	public static boolean isMysql(){
		return "mysql".equals(dbProps.get("SQLDialect")) ;
	}
	/**
	 * 生成Java类
	 * @author Elijah
	 * @param tableName
	 * @return
	 */
	public static String generateJavaClass(String tableName) {
		String sql = "select * from "+tableName +" where 1=2";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData meta = null;
		StringBuffer coding = new StringBuffer();
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			rs = getRs(conn, sql);
			meta = rs.getMetaData();
			
			coding.append("inport " + getJavaName(tableName) + "{\n");
			coding.append("public class " + getJavaName(tableName) + "{\n");
			coding.append("\n");
			
			int count;
		    try {
		      count = meta.getColumnCount();
		      for(int i = 1;i <= count; i++) {
	    	    String str = meta.getColumnName(i);
	    	    int type = meta.getColumnType(i);
		        //str[i].charAt(0) - 32)转成大写   思路
		        str = str.toLowerCase();
		        coding.append("\r \t public void set" + (char)(str.charAt(0) - 32)+ str.substring(1)+"("+getFieldType(type)+" "+str +"){");
		        coding.append("\r \t\t this."+str + "=" + str + ";");
		        coding.append("\r \t }");
		        coding.append("\r \t public "+getFieldType(type)+" get" + (char)(str.charAt(0) - 32)+ str.substring(1)+"(){");
		        coding.append("\r \t\t return this."+str +  ";");
		        coding.append("\r \t }\n");
		      }
		      coding.append("\n}");
		      return coding.toString();
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return coding.toString();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (SQLException e1) {
				e.printStackTrace();
			}
		}
		return coding.toString();
	}
	
	private static ResultSet getRs(Connection conn , String sql){
//		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
//			conn = getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(); // 查询下确定结果集是那个表的
			return rs ;
		}catch(SQLException e){
			return null;
		}
		
	}
}
