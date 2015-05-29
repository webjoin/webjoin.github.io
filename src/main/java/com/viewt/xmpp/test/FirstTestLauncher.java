package com.viewt.xmpp.test;


import java.io.BufferedReader;
import java.io.InputStreamReader;

//import org.jivesoftware.smack.Chat;
//import org.jivesoftware.smack.ChatManager;
//import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;


public class FirstTestLauncher {
	
	
//	public static void main(String[] args) throws Exception{
//		ConnectionConfiguration config = new ConnectionConfiguration("localhost", 5222);
//		config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
//		config.setDebuggerEnabled(false);
//		XMPPTCPConnection conn = new XMPPTCPConnection(config);
//		conn.connect();
//		conn.login("elijah", "123456");
//		ChatManager chatManager = ChatManager.getInstanceFor(conn);
//		
//		
//		final MessageListener messageListener = new MessageListener() {
//			@Override
//			public void processMessage(Chat chat, Message message) {
//				System.out.println("----"+message.toXML());
//				try {
//					chat.sendMessage(message.getBody());
//				} catch (NotConnectedException e) {
//					e.printStackTrace();
//				} catch (XMPPException e) {
//					e.printStackTrace();
//				}
//			}
//		};
//		
//		Chat chat = chatManager.createChat("tommy"+"@"+conn.getServiceName(), messageListener);
//		chatManager.addChatListener(new ChatManagerListener() {
//			@Override
//			public void chatCreated(Chat chat, boolean createdLocally) {
//				System.out.println("--"+chat+"--"+createdLocally);
//				chat.addMessageListener(messageListener);
//			}
//		});
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String line = null ;
//		while(!(line = br.readLine()).equals("bye")){
//			chat.sendMessage(line+"----");
//			System.out.println(line+"----");
//		}
//		conn.disconnect();
//		System.exit(0);
//	}

}
