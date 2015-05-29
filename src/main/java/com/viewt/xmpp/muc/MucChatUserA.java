package com.viewt.xmpp.muc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.InvitationRejectionListener;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;

/**
 * @author Elijah
 * 
 * API Smack 4.0.5 
 *
 */
public class MucChatUserA {

	static String domain = "openfire-test";
	static String host = "192.168.1.45" ;
	public static void main(String[] args) throws Exception {
//		4.0.5版本 smack 
//		host = "192.168.12.12" ;
////		XMPPTCPConnection
//		ConnectionConfiguration config = new ConnectionConfiguration(host, 5222);
//		config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
//		
//		
//		
////		final XMPPConnection conn2 = new XMPPTCPConnection(config);
////		conn2.connect();
////		conn2.login("jack", "e10adc3949ba59abbe56e057f20f883e","abc");
//		
//		
//		
//		final XMPPConnection conn1 = new XMPPTCPConnection(config);
//		conn1.connect();
//		conn1.login("jack", "e10adc3949ba59abbe56e057f20f883e","abc");
//		
////		conn1.loginAnonymously();
//		//创建房间
////		createRoom(conn1,"mucabc","muc");
// 
//		
////		邀请聊天
//		MultiUserChat muc2 = new MultiUserChat(conn1, "mucabc111@conference."+domain);
//		muc2.create("jack");
//		muc2.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
//		
////		muc2.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
//		
////		muc2.join("demo@openfire-test");
//		muc2.addInvitationRejectionListener(new InvitationRejectionListener() {
//			public void invitationDeclined(String invitee, String reason) {
//				// Do whatever you need here...
//				System.out.println(invitee+"==================="+reason);
//				//elijah@openfire-test===================No thank you
//			}
//		});
//		
//		
//		muc2.addMessageListener(new PacketListener() {
//			public void processPacket(Packet packet) throws NotConnectedException {
//				System.out.println("addMessageListener=="+packet.toXML());
//				if( packet instanceof Message){
//					Message msg = (Message)packet ;
//					String body = msg.getBody();
//				}
//			}
//		});
//		
//		muc2.addPresenceInterceptor(new PacketInterceptor() {
//			@Override
//			public void interceptPacket(Packet packet) {
//				System.out.println("addPresenceInterceptor----------"+packet);
//			}
//		});
//		/*这里可以监听到群组中的用户邀请成功和离线情况*/
//		muc2.addParticipantListener(new PacketListener() {
//			
//			@Override
//			public void processPacket(Packet packet) throws NotConnectedException {
//				//addParticipantListener----------available
//				//addParticipantListener----------<presence to='qaz@openfire-test/Smack' from='r222@conference.openfire-test/402881ad4c269967014c269a24ea0002-stone'><x xmlns="http://jabber.org/protocol/muc#user"><item affiliation="none" jid="402881ad4c269967014c269a24ea0002-stone@openfire-test/echat" role="participant"><reason></reason><actor jid=""/></item></x></presence>
//				//addParticipantListener----------<presence to='qaz@openfire-test/Smack' from='r222@conference.openfire-test/stone' type='unavailable'>					  <x xmlns="http://jabber.org/protocol/muc#user"><item affiliation="none" jid="stone@openfire-test/echat" role="none"><reason></reason><actor jid=""/></item></x></presence>
//				//addParticipantListener----------<presence id='ykjsy-31' to='jack@openfire-test/abc' from='mucabc111@conference.openfire-test/elijah' type='unavailable'><x xmlns="http://jabber.org/protocol/muc#user"><item affiliation="none" jid="elijah@openfire-test/Spark 2.6.3" role="none"><reason></reason><actor jid=""/></item></x></presence>
//				System.out.println("addParticipantListener----------"+packet.toXML());
//			}
//		});
//		MultiUserChat.addInvitationListener(conn1, new InvitationListener() {
//			public void invitationReceived(XMPPConnection conn, String room, String inviter, String reason,
//                    String password, Message message) {
//				System.out.println( conn+"-----"+   room +"-----"+ inviter+"-----"+reason+"-----"+password+"-----"+message);
//				//MultiUserChat.getJoinedRooms(conn1, arg1);
//				final MultiUserChat muc2 = new MultiUserChat(conn1, room);
//				try {
////					MultiUserChat.decline(conn, room, inviter, "起初我是拒绝的");
//					
//					muc2.join(conn1.getUser());
//					muc2.addMessageListener(new PacketListener() {
//						
//						@Override
//						public void processPacket(Packet packet) throws NotConnectedException {
//							if(packet instanceof Message){
//								Message message = (Message)packet;
//								if(!(muc2.getRoom()+"/"+conn1.getUser()).equals(message.getFrom())){
//									String body = message.getBody();
//									System.out.println(body);
//									//----------<message id="tWMnD-50" to="muctest@conference.openfire-test" type="groupchat" from="admin@openfire-test/Spark 2.6.3"><body>lll</body><x xmlns="jabber:x:event"><offline/><delivered/><displayed/><composing/></x></message>
//									Message me = new Message();
//									me.setBody(body+" 什么意思？");
////								me.setTo(muc2.getRoom());
////								me.setType(org.jivesoftware.smack.packet.Message.Type.groupchat);
//									try {
////									muc2.sendMessage(me);
//										muc2.sendMessage(body+" 什么意思？");
//									} catch (XMPPException e) {
//										e.printStackTrace();
//									}
//								}
//							}
//						}
//					});
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
//		muc2.invite("elijah@"+domain+"", "Meet me in this excellent room");
////		muc2.invite("demo@"+domain, "Meet me in this excellent room");
////		muc2.invite("402881ad4c269967014c269a24ea0002-stone@"+domain, "Meet me in this excellent room");
//
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String msg;
//		
//		System.out.println("-----");
//		System.out.println("Enter your message in the console:");
//		System.out.println("-----\n");
//
//		while (!(msg = br.readLine()).equals("bye")) {
//			
//			System.out.println(conn1.getUser());
//			if("dead".equals(msg)){
////				muc2.leave();
//				muc2.destroy("222", conn1.getUser());
//			}
//			System.out.println(msg);
//		}
//
//		conn1.disconnect();
//		System.exit(0);
	}
	/**
	 * @param conn1
	 * @param id 房间标志
	 */
//	private static void createRoom(XMPPConnection conn1, String id,String roomName) {
//		try {
//			MultiUserChat muc = new MultiUserChat(conn1,id+"@conference."+domain);
//			muc.create(conn1.getUser());
////		muc.join("muc-abc");
////		muc.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
//			Form form = muc.getConfigurationForm();
//			Form submitForm = form.createAnswerForm();
//			for (Iterator<FormField> fields = submitForm.getFields().iterator(); fields.hasNext();) {
//			    FormField field = (FormField) fields.next();
//			    if (!FormField.TYPE_HIDDEN.equals(field.getType()) && field.getVariable() != null) {
//			        submitForm.setDefaultAnswer(field.getVariable());
//			    }
//			}
////      submitForm.setAnswer("muc#roomconfig_publicroom", true);
//			submitForm.setAnswer("muc#roomconfig_roomname", roomName);
//			submitForm.setAnswer("muc#roomconfig_persistentroom", true);
//			muc.sendConfigurationForm(submitForm);
//		} catch (NoResponseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (XMPPErrorException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NotConnectedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SmackException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
