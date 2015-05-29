package com.viewt.xmpp.test;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Collection;

//import org.jivesoftware.smack.Chat;
//import org.jivesoftware.smack.ChatManager;
//import org.jivesoftware.smack.ConnectionConfiguration;
//import org.jivesoftware.smack.MessageListener;
//import org.jivesoftware.smack.Roster;
//import org.jivesoftware.smack.RosterEntry;
//import org.jivesoftware.smack.SmackException;
//import org.jivesoftware.smack.XMPPConnection; 
//import org.jivesoftware.smack.XMPPException;
//import org.jivesoftware.smack.SmackException.NotConnectedException;
//import org.jivesoftware.smack.packet.Message;
//import org.jivesoftware.smack.tcp.XMPPTCPConnection;
//import org.jivesoftware.smackx.workgroup.settings.WorkgroupProperties;
//import org.jivesoftware.smackx.workgroup.user.Workgroup;

public class WorkgroupQueue{


//	public void login(String workgroupName, String password) throws SmackException,
//			IOException, XMPPException {
//		// ConnectionConfiguration config = new
//		// ConnectionConfiguration("im.server.here",5222, "Work");
//		ConnectionConfiguration config = new ConnectionConfiguration("localhost", 5222);
//		config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
//		config.setDebuggerEnabled(true);  
//		
//		//模拟坐席登陆
//		XMPPConnection conn = new XMPPTCPConnection(config);
//		conn.connect();
////		conn.login(workgroupName, password);
//		conn.loginAnonymously();
//		System.out.println(conn.isConnected());
//		Workgroup workgroup = new Workgroup("group_297ed0d04a9f11cc014a9f149e6d0003-elijah@workgroup.openfire-test",conn);
////		workgroup.joinQueue();
////		WorkgroupProperties workgroupProperties = workgroup.getWorkgroupProperties();
////		System.out.println(workgroupProperties);
//		System.out.println("-------------"+workgroup.isAvailable());
//		System.out.println("--------------------------------");
////		//机器人登录
////		connection = new XMPPTCPConnection(config);
////		connection.connect();
////		connection.loginAnonymously();
//		
////		System.out.println("匿名登录后的ID:"+connection.getUser());
////		String serviceName = connection.getServiceName();
////		String workgroup1 = "group_" + workgroupName + "@workgroup." + serviceName;
////		Workgroup workgroup = new Workgroup(workgroup1, connection);
////		WorkgroupProperties workgroupProperties = workgroup.getWorkgroupProperties();
////		System.out.println(workgroupProperties);
//	}
//
//
//	public void processMessage(Chat chat, Message message) {
////		if (message.getType() == Message.Type.chat)
//			System.out.println(chat.getParticipant() + " says: "
//					+ message.getBody());
//	}
//
//	public static void main(String args[]) throws XMPPException, IOException,
//			SmackException {
//		// declare variables
////		JabberSmackAPI c = new JabberSmackAPI();
//		WorkgroupQueue c = new WorkgroupQueue();
//
//		// turn on the enhanced debugger
//		// XMPPConnection.DEBUG_ENABLED = true;
//
//		// Enter your login information here
//		c.login("spark_1", "123456");
////		c.login("demo", "demo");
//
////		c.displayBuddyList();
//
////		System.out.println("-----");
////
////		System.out
////				.println("Who do you want to talk to? - Type contacts full email address:");
////		String talkTo = br.readLine();
////
////		System.out.println("-----");
////		System.out.println("All messages will be sent to " + talkTo);
////		System.out.println("Enter your message in the console:");
////		System.out.println("-----\n");
////
////		while (!(msg = br.readLine()).equals("bye")) {
////			c.sendMessage(msg, talkTo);
////		}
////
////		c.disconnect();
////		System.exit(0);
//	}


}
