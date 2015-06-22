package com.viewt.gb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.viewt.gb.file.FileBean;

@WebServlet(name="OperateResource",urlPatterns={"/gb"},asyncSupported=true)
@MultipartConfig //文件上传
public class OperateResource extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -536282380793470108L;

	public void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{    
		String rs = "{success:${success},msg:${msg}}";
		String dir = "/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt";
		dir  = request.getParameter("path");
		FileBean bean = new FileBean();
		FileBean[] beann;
		PrintWriter out = response.getWriter();
		try {
			beann = bean.getFile1(dir);
			bean.setName( dir );
			bean.setSubDirs(beann) ;
			JSONObject obj = new JSONObject(bean);
			out.print(rs.replace("${success}", "true").replace("${msg}", obj.toString()));
		} catch (Exception e) {
			out.print(rs.replace("${success}", "false").replace("${msg}", "'"+e.getMessage()+"'"));
		}
//		out.print( "{success:true,msg:{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/.DS_Store\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/btrace/Helloworld.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/btrace/TraceHelloWorld.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/btrace\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/container/ListFeatures.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/container\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/file/FileUtils.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/file\"},{\"subDirs\":[{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb/file/FileBean.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb/file\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb/servlet/OperateResource.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb/servlet\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/gb\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/hazelcast/hazelcast.xml\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/hazelcast/Hazelcast1Client.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/hazelcast/Hazelcast1Server.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/hazelcast\"},{\"subDirs\":[{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/another/ConSumerTest.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/another/ProducerTest.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/another\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/ConsumerDemo.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/KafkaConsumer.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/KafkaProducer.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/PartitionerDemo.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka/ProducerDemo.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/kafka\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/log/LogUtil.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/log\"},{\"subDirs\":[{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/netty/bio/TimeServer.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/netty/bio\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/netty\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/ping/Ping.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/ping\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/redis/RedisCluster.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/redis/RedisHelloworld.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/redis\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/string/StringIndexPractice.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/string\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/websocket/HelloWebSocket.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/websocket/MucWebSocket.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/websocket\"},{\"subDirs\":[{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/chat/P2pChat.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/chat/P2pChatB.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/chat\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/form/Bootstrap.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/form\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/muc/MucChatUserA.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/muc/UccCustomer.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/muc\"},{\"subDirs\":[{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/test/FirstTestLauncher.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/test/JabberSmackAPI.java\"},{\"subDirs\":[],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/test/WorkgroupQueue.java\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp/test\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt/xmpp\"}],\"name\":\"/Users/Elijah/Learning/git-repo/Jerusalem/src/main/java/com/viewt\"}}");
		
		out.flush();
	}

}
