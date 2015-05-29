package com.viewt.xmpp.muc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.PacketExtension;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Type;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;

/**
 * API Smack 4.0.5 
 * @author Elijah
 * 模拟访客登录的类 
 *
 */
public class UccCustomer {
	
//	private static final String EC = "ec";
//	private static final String ID_PREFIX = "SR_";
//	
//	private static final String ECHAT_EC_RECORD = "echat:ec:record";
//	private static final String ECHAT_EC_STATUS = "echat:ec:status";
//	 
//	public static void main(String[] args) throws Exception{
//		invokeUcc();
//		
////		sigleLogin();
//	}
//	public static void sigleLogin()throws Exception{
//		long start = System.currentTimeMillis() ;
//		Collection<Callable<XMPPConnection>> tasks = new ArrayList<Callable<XMPPConnection>>();
//		for (int i = 0; i < 10000 ; i++) {
//			tasks.add(new Callable<XMPPConnection>() {
//				public XMPPConnection call() throws Exception {
//					ConnectionConfiguration config = new ConnectionConfiguration("192.168.1.191",5222,"openfire-test");
//					config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
//					XMPPConnection conn = new XMPPTCPConnection(config);
//					conn.connect();
//					conn.loginAnonymously();
//					return conn;
//				}
//			});
//		}
//		
//		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(4);
//		List<Future<XMPPConnection>> invokeFutures = newFixedThreadPool.invokeAll(tasks);
//		System.out.println("10000匿名用户登录完成 耗时"+(System.currentTimeMillis()-start)+"ms");
//		Iterator<Future<XMPPConnection>> iterator = invokeFutures.iterator();
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String msg;
//		System.out.println("-----");
//		System.out.println("Enter your message in the console:");
//		System.out.println("-----\n");
//
//		while (!(msg = br.readLine()).equals("bye")) {
//			System.out.println(msg);
//		}
//		
//		while(iterator.hasNext()){
//			XMPPConnection xmppConnection = iterator.next().get();
//			xmppConnection.disconnect();
//		}
//		
//		
//		System.exit(0);
//		
//		
//	}
//	static java.util.concurrent.atomic.AtomicInteger count = new AtomicInteger(0);
//	public static void invokeUcc()throws Exception{
//		final String opname = "402881954bfe786a014bfe7974080002-j";
//		
////		final String opname = "jack";
//		ConnectionConfiguration config = new ConnectionConfiguration("192.168.1.191",5222,"openfire-test");
//		config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
//		XMPPConnection conn = new XMPPTCPConnection(config);
//		conn.connect();
//		conn.login(opname, "123456");
//		
//		Presence presence = new Presence(Type.available);
//		presence.setStatus("online");
//		presence.addExtension(new ChatExtension());
//		conn.sendPacket(presence);
//
//		System.out.println("config.getUsername() -----------"+ config.getUsername());
//		System.out.println("conn.getUser()-----------"+ conn.getUser());
//		
//		//接受访客的邀请
//		MultiUserChat.addInvitationListener(conn, new InvitationListener() {
//			public void invitationReceived(XMPPConnection conn1, String room,
//					String inviter, String reason, String password, Message message) {
//				final MultiUserChat muc = new MultiUserChat(conn1, room);
//				try {
//					muc.join(opname);
//					System.out.println("第 "+count.addAndGet(1) +" 次被邀请 " +(new java.sql.Timestamp(System.currentTimeMillis()))+"------接受邀请=============="+message.toXML());
//				} catch (NoResponseException e) {
//					e.printStackTrace();
//				} catch (XMPPErrorException e) {
//					e.printStackTrace();
//				} catch (NotConnectedException e) {
//					e.printStackTrace();
//				}
//				muc.addMessageListener(new PacketListener() {
//					public void processPacket(Packet packet) throws NotConnectedException {
//						if(packet instanceof Message){
//							Message message = (Message) packet;
//							if(!message.getFrom().contains(opname)){  //发送者不包含自己
////								message.getChildElement(EC, ECHAT_EC_STATUS);
//								PacketExtension extension = message.getExtension(EC, "echat:ec"); 
//								if(extension != null) {
//									//表示访客发送的提示信息
//									return ;
//								}
//								System.out.println(new java.sql.Timestamp(System.currentTimeMillis())+"========"+packet.toXML());
//								String body = message.getBody() +" --起初我是拒绝的";
//								try {
//									muc.sendMessage(body);
//								} catch (XMPPException e) {
//									e.printStackTrace();
//								}
//							}
//						}
//					}
//				});
//			}
//		});
//		
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String msg;
//		
//		System.out.println("-----");
//		System.out.println("Enter your message in the console:");
//		System.out.println("-----\n");
//
//		while (!(msg = br.readLine()).equals("bye")) {
//			System.out.println(msg);
//		}
//
//		conn.disconnect();
//		System.exit(0);
//	
//	}
//}
//
//class ChatExtension implements PacketExtension {
//	private String type;
//
//	public ChatExtension() {
//
//	}
//
//	public ChatExtension(String type) {
//		this.type = type;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.jivesoftware.smack.packet.PacketExtension#getElementName()
//	 */
//	@Override
//	public String getElementName() {
//		// TODO Auto-generated method stub
//		return "ec";
//	}
//
//	/* (non-Javadoc)
//	 * @see org.jivesoftware.smack.packet.PacketExtension#getNamespace()
//	 */
//	@Override
//	public String getNamespace() {
//		// TODO Auto-generated method stub
//		return "echat:ec:status";
//	}
//
//	public String getType() {
//		return type;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	/* (non-Javadoc)
//	 * @see org.jivesoftware.smack.packet.PacketExtension#toXML()
//	 */
//	@Override
//	public String toXML() {
//		// TODO Auto-generated method stub
//		StringBuffer buf = new StringBuffer();
//		buf.append("<").append(getElementName()).append(" xmlns=\"").append(getNamespace())
//				.append("\" type=\"" + type + "\" />");
//		return buf.toString();
//	}

}
