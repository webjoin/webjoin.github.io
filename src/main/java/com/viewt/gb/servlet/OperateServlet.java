package com.viewt.gb.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.viewt.gb.I1n8ThreadLocal;
import com.viewt.gb.Util;
import com.viewt.gb.file.FileBean;

@WebServlet(name = "OperateResource", urlPatterns = { "/index.html" }, asyncSupported = true, loadOnStartup = 1)
@MultipartConfig
// 文件上传
public class OperateServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -536282380793470108L;
	
	

	@Override
	public void destroy() {
		super.destroy();
	}


	boolean isInit = true;
	
	@Override
	public void init() throws ServletException {
		super.init();
		File file = new File(Util.gbFile);
		if(file.exists()){
			try {
				Util.initDB();
				Connection connection = Util.getConnection();
				Util.closeConnection(connection);
			} catch (Exception e) {
				e.printStackTrace();
				isInit = false;
			}
		}
		if (!file.exists() || !isInit) {
			try {
				Util.copy(Util.class.getResource("").getPath()+".gb.properties",Util.gbFile);
				//Util.initProperties();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		initI18n();
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		System.out.println(stack[0].getClassName()+"--"+stack[0].getMethodName());
		System.out.println("Servlet is ready");
		System.out.println("isInit:"+isInit);
	}
	public void initI18n(){
		try {
			InputStream en = new FileInputStream(Util.zhFile);
			Util.zhProps.load(en);
			en.close();
			
			InputStream zh = new FileInputStream(Util.enFile);
			Util.enProps.load(zh);
			zh.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		if(op == null || "".equals(op)){
			op = "index";
		}
		Method method = null ;
		try {
			method = OperateServlet.class.getMethod(op, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response);
		} catch (NoSuchMethodException | SecurityException   | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
		// 查询用户下所有的表
		// 查询表下面所有的字段
	}
	
	/**
	 * 用户今天查看到的界面
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws Exception
	 */
	public void index(HttpServletRequest request, HttpServletResponse response) throws IOException,Exception {
		String i18n = "zh";
		if(null != request.getParameter("i18n")  && !"".equals(request.getParameter("i18n"))) { //国际化
			i18n = request.getParameter("i18n");
		}
		if(!I1n8ThreadLocal.get().getProperty("i18n").equals(i18n)){
			if("zh".equals(i18n))
				I1n8ThreadLocal.set(Util.zhProps);
			else if("en".equals(i18n))
				I1n8ThreadLocal.set(Util.enProps);
			else 
				throw new Exception(I1n8ThreadLocal.get().getProperty("not.support"));
		}
//		if(this.isInit){ //false
			request.setAttribute("isInit", this.isInit);
			request.setAttribute("i18n", I1n8ThreadLocal.get());
			if(this.isInit)
				request.setAttribute("tables", Util.queryTables(Util.getQueryTablesSql()));
			//queryTables
//			response.sendRedirect(request.getContextPath()+"/com/viewt/gb/index.jsp");
			request.getRequestDispatcher("/com/viewt/gb/index.jsp").forward(request, response);
//		}
	}
	private String rs = "{\"success\":${success},\"msg\":${msg}}"; 
	/**保存数据源的配置
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	public void initDB(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String ip = request.getParameter("ip");
		String username = request.getParameter("username");
		String passcode = request.getParameter("passcode");
		String SQLDialect = request.getParameter("SQLDialect");
		String driver = request.getParameter("driver");
		String db = request.getParameter("db");
		String src_addr = request.getParameter("src_addr");
		// jdbc:mysql://localhost:3306/openfire?useUnicode=true&characterEncoding=UTF8
		// jdbc:oracle:thin:@127.0.0.1:1521:ORCL
		String url = "";
		if ("mysql".equals(SQLDialect)) {
			url = "jdbc:mysql://" + ip + ":3306/" + db+ "?useUnicode=true&characterEncoding=UTF8";
		} else if ("oracle".equals(SQLDialect)) {
			url = "jdbc:oracle:thin:@" + ip + ":1521:ORCL";
		}

		Util.setProperty("ip", ip);
		Util.setProperty("SQLDialect", SQLDialect); // sql方言
		Util.setProperty("url", url);
		Util.setProperty("username", username);
		Util.setProperty("password", passcode);
		Util.setProperty("driverClassName", driver);
		Util.setProperty("db", db);
		Util.setProperty("src_addr", src_addr);
		try {
			FileOutputStream fos = new FileOutputStream(Util.gbFile,false);
			FileChannel fc = fos.getChannel();
			Properties props = Util.dbProps;
			Set<Map.Entry<Object,Object>> map = props.entrySet();
			Iterator<Entry<Object, Object>> iterator = map.iterator();
			while(iterator.hasNext()){
				Entry<Object, Object> next = iterator.next();
				String line = next.getKey()+"="+next.getValue()+"\r\n";
				fc.write(ByteBuffer.wrap(line.getBytes()));
			}
			fc.close();
			fos.flush();
			fos.close();
			try {
				Util.initDB();
				Connection connection = Util.getConnection();
				Util.closeConnection(connection);
				isInit = true;
			} catch (Exception e) {
				isInit = false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		if(isInit){
			out.append(rs.replace("${success}", "true").replace("${msg}", "\""+I1n8ThreadLocal.get().getProperty("db.init.success")+"\""));
		}else{
			out.append(rs.replace("${success}", "false").replace("${msg}", "\""+I1n8ThreadLocal.get().getProperty("db.init.error")+"\""));
		}
		out.flush();
	}
	
	public static void main(String[] args) {
//			Path path = new Path();
			try {
				FileChannel fc = new FileOutputStream(Util.gbFile).getChannel();
				fc.write(ByteBuffer.wrap("abcd----sbsdsd\r\n".getBytes()));
				fc.write(ByteBuffer.wrap("trtrtrt---ab000\r\n".getBytes()));
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void loadFiles(HttpServletRequest request, HttpServletResponse response){
		String dir = "/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt";
//		dir = request.getParameter("src_addr");
		dir = Util.getProperty("src_addr");
		PrintWriter out = null ;
		FileBean bean = new FileBean();
		FileBean[] beann;
		JSONObject obj = null ;
		if(null != dir && !"".equals(dir)){
			try {
				out = response.getWriter();
				beann = bean.getFile1(dir);
				bean.setName(dir);
				bean.setSubDirs(beann);
				obj = new JSONObject(bean);
				out.print(rs.replace("${success}", "true").replace("${msg}",obj.toString()));
			} catch (Exception e) {
				out.print(rs.replace("${success}", "false").replace("${msg}", "'" + e.getMessage() + "'"));
			}
				out.flush();
		}else{
			//TODO ....
		}
	}
	
//	public 
}
