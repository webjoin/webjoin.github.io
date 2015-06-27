package com.viewt.gb.servlet;

import java.io.File;
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

@WebServlet(name = "OperateResource", urlPatterns = { "/gb" }, asyncSupported = true, loadOnStartup = 1)
@MultipartConfig
// 文件上传
public class OperateResource extends HttpServlet {
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
		File file = new File(Util.gbFile);
		if(file.exists()){
			try {
				Util.initDB();
				Connection connection = Util.getConnection();
				Util.closeConnection(connection);
			} catch (Exception e) {
				isInit = false;
			}
		}
		if (!file.exists() || !isInit) {
			try {
				Util.copy(Util.class.getResource("").getPath()+".gb.properties",Util.gbFile);
				Util.initProperties();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		initI18n();
		super.init();
	}
	public void initI18n(){
		try {
			InputStream en = Util.class.getResourceAsStream(Util.enFile);
			Util.zhProps.load(en);
			en.close();
			
			InputStream zh = Util.class.getResourceAsStream(Util.enFile);
			Util.enProps.load(zh);
			zh.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = request.getParameter("op");
		Method method = null ;
		try {
			method = OperateResource.class.getMethod(op, HttpServletRequest.class,HttpServletResponse.class);
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
		if(request.getParameter("i18n") == null || "".equals(request.getParameter("i18n"))) { //国际化
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
		if(!this.isInit){ //false
			request.setAttribute("isInit", this.isInit);
			request.setAttribute("i18n", I1n8ThreadLocal.get());
			response.sendRedirect("/com/viewt/gb/index.jsp");
			request.getRequestDispatcher("/com/viewt/gb/index.jsp").forward(request, response);
		}
	}
	/**保存数据源的配置
	 * @param request
	 * @param response
	 */
	public void initDB(HttpServletRequest request, HttpServletResponse response){
		String ip = request.getParameter("ip");
		String username = request.getParameter("username");
		String passcode = request.getParameter("passcode");
		String SQLDialect = request.getParameter("SQLDialect");
		String driver = request.getParameter("driver");
		String sid = request.getParameter("sid");
		String src_addr = request.getParameter("src_addr");
		// jdbc:mysql://localhost:3306/openfire?useUnicode=true&characterEncoding=UTF8
		// jdbc:oracle:thin:@127.0.0.1:1521:ORCL
		String url = "";
		if ("mysql".equals(SQLDialect)) {
			url = "jdbc:mysql://" + ip + ":3306/" + sid+ "?useUnicode=true&characterEncoding=UTF8";
		} else if ("oracle".equals(SQLDialect)) {
			url = "jdbc:oracle:thin:@" + ip + ":1521:ORCL";
		}

		Util.setProperty("ip", ip);
		Util.setProperty("SQLDialect", SQLDialect); // sql方言
		Util.setProperty("url", url);
		Util.setProperty("username", username);
		Util.setProperty("password", passcode);
		Util.setProperty("driverClassName", driver);
		Util.setProperty("sid", sid);
		Util.setProperty("src_addr", src_addr);
		try {
			FileOutputStream fos = new FileOutputStream(Util.gbFile,false);
			FileChannel fc = fos.getChannel();
			Properties props = Util.props;
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
			} catch (Exception e) {
				isInit = true;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
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
		String rs = "{success:${success},msg:${msg}}";
		String dir = "/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt";
		dir = request.getParameter("src_addr");
		FileBean bean = new FileBean();
		FileBean[] beann;
		PrintWriter out = null ;
		try {
			out = response.getWriter();
			beann = bean.getFile1(dir);
			bean.setName(dir);
			bean.setSubDirs(beann);
			JSONObject obj = new JSONObject(bean);
			out.print(rs.replace("${success}", "true").replace("${msg}",obj.toString()));
		} catch (Exception e) {
			out.print(rs.replace("${success}", "false").replace("${msg}", "'" + e.getMessage() + "'"));
		}
		// out.print(
		// "{success:true,msg:{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/.DS_Store\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/btrace/Helloworld.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/btrace/TraceHelloWorld.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/btrace\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/container/ListFeatures.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/container\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/file/FileUtils.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/file\"},{\"subDirs\":[{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb/file/FileBean.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb/file\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb/servlet/OperateResource.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb/servlet\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/hazelcast/hazelcast.xml\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/hazelcast/Hazelcast1Client.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/hazelcast/Hazelcast1Server.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/hazelcast\"},{\"subDirs\":[{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/another/ConSumerTest.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/another/ProducerTest.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/another\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/ConsumerDemo.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/KafkaConsumer.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/KafkaProducer.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/PartitionerDemo.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/ProducerDemo.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/log/LogUtil.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/log\"},{\"subDirs\":[{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/netty/bio/TimeServer.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/netty/bio\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/netty\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/ping/Ping.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/ping\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/redis/RedisCluster.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/redis/RedisHelloworld.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/redis\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/string/StringIndexPractice.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/string\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/websocket/HelloWebSocket.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/websocket/MucWebSocket.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/websocket\"},{\"subDirs\":[{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/chat/P2pChat.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/chat/P2pChatB.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/chat\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/form/Bootstrap.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/form\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/muc/MucChatUserA.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/muc/UccCustomer.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/muc\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/test/FirstTestLauncher.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/test/JabberSmackAPI.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/test/WorkgroupQueue.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/test\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt\"}}");
		if(out != null)
			out.flush();
	}
	
//	public 
}
