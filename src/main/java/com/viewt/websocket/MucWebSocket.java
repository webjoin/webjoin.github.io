package com.viewt.websocket;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.PresenceListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatManager;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.json.JSONObject;

import com.sun.btrace.annotations.OnError;
import com.sun.jmx.snmp.Timestamp;

@ServerEndpoint("/muc-websocket")
public class MucWebSocket {
	
	private static Map<String,AbstractXMPPConnection> conn_map = new HashMap<String,AbstractXMPPConnection>();
	private static Map<String,Session> sess_map = new HashMap<String,Session>();
//	private static Map<String,MultiUserChatManager> muc_mgr_map = new HashMap<String,MultiUserChatManager>();
	private final static String CONNECTION_KEY = "connection_key";
	private final static String SESSION_KEY = "session_key";
	private final static String MUC_KEY = "muc_key";
//	private final static String MUC_KEY = "muc_key";
	//@openfire-test/viewt
	private final static String RESOURCE = "viewt";
	private final static String DOMAIN_NAME = "@openfire-test/viewt";
	private final static String CONFERENCE_NAME = "@conference.openfire-test";
	private final static String  rs = "var rs = {type:'${type}',nickname:'${nickname}',mucname:'${mucname}',msg:'${msg}',reason:'${reason}'},sessionID:'${sessionID}'}"; 
	private Session session = null ;
	public enum MsgType{
		Empty(),
		Invite(),
		Create_muc(),
		Msg();
		private MsgType(){}

		@Override
		public String toString() {
			return super.toString();
		}
	}
	
	public static void main(String[] args) {
//		System.out.println(MsgType.Empty);
//		JsonObject object = JsonObject.readFrom("{\"type\":\"empty\"}");
//		System.out.println(object.get("type"));
		JSONObject json = new JSONObject("{\"type\":\"empty\"}");
//		System.out.println(json.toJSONString());
		System.out.println(json.get("type"));
	}
	/**
	 * @param message
	 * 			{
	 * 			type:
	 * 			nickname:
	 * 			invitationName: //
	 * 			receive:
	 * 			msg:
	 * 			muc:
	 * }
	 * @param session
	 */
	@OnMessage
	public String onMessage(String message, Session session){
		String rs = null ;
		JSONObject object = null ;
		String sessionID = session.getId();
		object = new JSONObject(message);
		switch((String)object.get("type")){
			case "nickname":
				login(object,session);
			case "empty" :
				System.out.println(new Timestamp(System.currentTimeMillis())+"--HeartBeat--"+session.getId());
				break;
			case "create_muc" :
				rs = createMuc(object,sessionID);
				break;
			case "invite" :
				rs = invite(object,sessionID);
				break;
			case "msg" :
				rs = msg(object);
				break;
			case "receive": //接受邀请
				rs = receive(object,sessionID);
				break ;
			case "broadcast" :
				rs = broadcast(object,sessionID);
				break;
			default:
				System.out.println("-------Do nothing");
				break;
		}
		return rs;
	}
	/**房间创建
	 * @param object
	 * @return
	 */
	public String createMuc(JSONObject object,String sessionID){
		AbstractXMPPConnection connection = conn_map.get(this.session.getId());
		String mucName= connection.getUser()+"-MUC"+CONFERENCE_NAME;
		String nickName = (String)object.get("nickname");
		MultiUserChat muc =  null ;
		try {
			muc = MultiUserChatManager.getInstanceFor(connection).getMultiUserChat(mucName);
			muc.create("test");
			muc.sendConfigurationForm(new Form(DataForm.Type.submit));
			muc.join(nickName);  //加入房间
			
			//如果成员被邀请进来则通知
			muc.addParticipantListener(new PresenceListener() {
				@Override
				public void processPresence(Presence presence) {
					//监听成员的状态
					//broadcast(object, sessionID);
					Collection<Session> values = sess_map.values();
//					String from = presence.getFrom();
					Iterator<Session> iterator = values.iterator();
//					String rs1 = rs.replace("${type}", "broadcast").replace("${nickname}", nickname).replace("${sessionID}", sessionID);
					while(iterator.hasNext()){
						try {
							Session next = iterator.next();
							if(next.isOpen())
								next.getBasicRemote().sendText(rs.replace("${type}", "ParticipantStatus"));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			});
			sess_map.get(sessionID).getBasicRemote().sendText(rs.replace("${type}","createMuc").replace("${mucname}", mucName));
		} catch ( XMPPErrorException  | SmackException | IOException e1) {
			e1.printStackTrace();
		}
		return mucName ;
	}
	
	/**
	 * 邀请人员
	 * @param object
	 * @return
	 */
	public String invite(JSONObject object,String sessionID){
		//被邀请人的名称
		String invitedName = object.get("invitedName")+DOMAIN_NAME;
		//邀请原因
		String reason = (String)object.get("reason");
		//房间名称
		String mucname = (String)object.get("mucname");
		AbstractXMPPConnection connection = conn_map.get(sessionID);
		MultiUserChat muc2 = MultiUserChatManager.getInstanceFor(connection).getMultiUserChat(mucname);
		try {
			//邀请 invitedName
			muc2.invite(invitedName, reason);
		} catch (NotConnectedException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 被邀请的人 加入房间 (加入到房间)
	 * 
	 * @param object
	 * @return
	 */
	public String receive(JSONObject object,String sessionID){
		String nickname = (String)object.get("nikename");
		String mucname = (String)object.get("mucname");
		AbstractXMPPConnection conn = conn_map.get(sessionID);
		MultiUserChatManager mucMgr = MultiUserChatManager.getInstanceFor(conn);
		MultiUserChat multiChat = mucMgr.getMultiUserChat(mucname);
		try {
			multiChat.join(nickname);
			//
		} catch (NoResponseException | XMPPErrorException | NotConnectedException e) {
			e.printStackTrace();
		}
		return null ;
	}
	public String msg(JSONObject object){
		return null ;	
	}
	/**上线 广播消息
	 * @param object	{
	 * 			nickname
	 * }
	 * @param sessionID
	 * @return
		private final static String  rs = "var rs = {type:'${0}',nickname:'${1}',mucname:'${2}',msg:'${3}'}";
	 */
	public String broadcast(JSONObject object,String sessionID){
//		Session session = sess_map.get(sessionID);
		//session.getBasicRemote().sendText("");
		String nickname = (String)object.get("nickname");
		Collection<Session> values = sess_map.values();
		Iterator<Session> iterator = values.iterator();
		//{type:'broadcast',nick:''}
		String rs1 = rs.replace("${type}", "broadcast").replace("${nickname}", nickname).replace("${sessionID}", sessionID);
		while(iterator.hasNext()){
			try {
				Session sess = iterator.next();
				if(sess.isOpen() && !sess.getId().equals(sessionID)){  //排除当前客户端
					sess.getBasicRemote().sendText(rs1);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null ;
	}

	
	@OnOpen
	public void onOpen(Session session){
		this.session =	session;
		System.out.println("----------------"+session.getId());
	}
	@OnError
	public void onErr(Session session,Throwable e){
		e.printStackTrace();
	}
	
	public void login(JSONObject json , Session sessionID){
		
		String username= (String)json.get("nickname"),//"elijah", 
				password= "123456",
				resource = "viewt",
				serviceName = "openfire-test",
//				host = "192.168.1.117";
				host = "127.0.0.1";
		XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
//				  .setUsernameAndPassword(username, password)
				  .setServiceName(serviceName)
				  .setHost(host)
				  .setPort(5222)
//				  .setDebuggerEnabled(true)
				  .setSecurityMode(SecurityMode.disabled)
				  .allowEmptyOrNullUsernames()
				  .build();
		AbstractXMPPConnection conn = new XMPPTCPConnection(config);
		MultiUserChatManager mucMgr = null ;
		InvitationListener invitationListener = null ;
		try {
			conn.connect();
//			conn.login();
			conn.login(username, password,resource);
			conn_map.put(session.getId(), conn);
			sess_map.put(session.getId(), session);
			mucMgr = MultiUserChatManager.getInstanceFor(conn);
			//邀请监听 A->B
			invitationListener = new InvitationListener() {
				@Override
				public void invitationReceived(XMPPConnection conn, MultiUserChat room,
						String inviter, String reason, String password, Message message) {
					//inviter--conn
					//A---------->B
//					java.util.logging.Logger.getLogger("").
					//邀请原因 存放 对方的sessionID
					JSONObject jsonObject = new JSONObject(reason);
					String sessionID = (String)jsonObject.get("sessionID"); //被邀请人的sessionID
					String nickname = (String)jsonObject.get("nickname");   //邀请人昵称
					String reason1 = (String)jsonObject.get("reason");	   //邀请原因
					try {
						//= "var rs = {type:'${0}',nickname:'${1}',mucname:'${2}',msg:'${3}'}"
						String rs1 = rs.replace("${0}", "invite").replace("${1}", nickname).replace("${reason}", reason1);
						//发送消息通知对方
						sess_map.get(sessionID).getBasicRemote().sendText(rs1);
					} catch (IOException e) {
						e.printStackTrace();
					}
//					(conn2, room, inviter, "I'm busy right now");
//					try {
//						room.join(conn.getUser());
//					} catch (NoResponseException e) {
//						e.printStackTrace();
//					} catch (XMPPErrorException e) {
//						e.printStackTrace();
//					} catch (NotConnectedException e) {
//						e.printStackTrace();
//					}
				}
			};
			mucMgr.addInvitationListener(invitationListener);
			System.out.println("-------"+conn.getStreamId()+"--"+session.getId());
		}catch(Exception e){
			e.printStackTrace();
			conn = null;
			conn_map.remove(session.getId());
			sess_map.remove(session.getId());
			if(mucMgr != null)
				mucMgr.removeInvitationListener(invitationListener);
		}
	}

}
