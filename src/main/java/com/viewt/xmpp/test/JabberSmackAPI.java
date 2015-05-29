package com.viewt.xmpp.test;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Collection;
//
//import org.jivesoftware.smack.Chat;
//import org.jivesoftware.smack.ChatManager;
//import org.jivesoftware.smack.ChatManagerListener;
//import org.jivesoftware.smack.ConnectionConfiguration;
//import org.jivesoftware.smack.MessageListener;
//import org.jivesoftware.smack.PacketCollector;
//import org.jivesoftware.smack.PacketListener;
//import org.jivesoftware.smack.Roster;
//import org.jivesoftware.smack.RosterEntry;
//import org.jivesoftware.smack.SmackException;
//import org.jivesoftware.smack.SmackException.NotConnectedException;
//import org.jivesoftware.smack.XMPPConnection;
//import org.jivesoftware.smack.XMPPException;
//import org.jivesoftware.smack.filter.AndFilter;
//import org.jivesoftware.smack.filter.PacketFilter;
//import org.jivesoftware.smack.filter.PacketTypeFilter;
//import org.jivesoftware.smack.packet.Message;
//import org.jivesoftware.smack.packet.Packet;
//import org.jivesoftware.smack.tcp.XMPPTCPConnection;
//import org.jivesoftware.smackx.xevent.MessageEventNotificationListener;

//public class JabberSmackAPI implements PacketListener,MessageListener,MessageEventNotificationListener {
//
//	
//	
//	
//	@Override
//	public void processPacket(Packet packet) throws NotConnectedException {
//		
//		System.out.println("processPacket--------:"+packet.toXML());
//		
//		
//	}
//
//	//---------------------------------------------------------
//	@Override
//	public void cancelledNotification(String arg0, String arg1) {
//		System.out.println("cancelledNotification");
//		
//	}
//
//	@Override
//	public void composingNotification(String arg0, String arg1) {
//		System.out.println("composingNotification");
//	}
//
//	@Override
//	public void deliveredNotification(String arg0, String arg1) {
//		System.out.println("deliveredNotification");
//	}
//
//	@Override
//	public void displayedNotification(String arg0, String arg1) {
//		System.out.println("displayedNotification");
//	}
//	
//
//	@Override
//	public void offlineNotification(String arg0, String arg1) {
//		System.out.println("offlineNotification");
//	}
// 
//	XMPPConnection connection;
//
//	public void login(String workgroupName, String password) throws SmackException,
//			IOException, XMPPException {
//		ConnectionConfiguration config = new ConnectionConfiguration("localhost", 5222, "Work");
//		config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
//		config.setDebuggerEnabled(true);
//		
//		//Ä£Äâ×øÏ¯µÇÂ½
//		connection = new XMPPTCPConnection(config);
//		connection.connect();
//		connection.login(workgroupName, password);
//		ChatManager chatManager = ChatManager.getInstanceFor(connection);
//		chatManager.addChatListener(new ChatManagerListener() {
//			@Override
//			public void chatCreated(Chat chat, boolean createdLocally) {
//				System.out.println("-----------"+chat.getParticipant() + " says: " + chat.getThreadID());
//				chat.addMessageListener(new MessageListener() {
//					@Override
//					public void processMessage(Chat chat, Message message) {
//							System.out.println("--------------------------"+message.toXML());
//					}
//				});
//			}
//		});
//	}
//
//	public void sendMessage(String message, String to)throws NotConnectedException, XMPPException {
//		ChatManager chatManager = ChatManager.getInstanceFor(connection);
////		Chat chat = chatManager.createChat(to, this);
//		 Chat chat = chatManager.createChat(to, this);
////		Message message1 = new Message(to);
////		message1.setBody(message);
//		chat.sendMessage(message);
////		chat.sendMessage(message1);
//	}
//
//	public void displayBuddyList() {
//		Roster roster = connection.getRoster();
//		Collection<RosterEntry> entries = roster.getEntries();
//
//		System.out.println("\n\n" + entries.size() + " buddy(ies):");
//		for (RosterEntry r : entries) {
//			System.out.println(r.getUser());
//		}
//	}
//
//	public void disconnect() throws NotConnectedException {
//		connection.disconnect();
//	}
//
//	public void processMessage(Chat chat, Message message) {
////		if (message.getType() == Message.Type.chat)
//		System.out.println("processMessage------" + message.toXML());
//			System.out.println(chat.getParticipant() + " says: " + message.getBody());
//	}
//
//	public static void main(String args[]) throws XMPPException, IOException,
//			SmackException {
//		// declare variables
//		JabberSmackAPI c = new JabberSmackAPI();
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		String msg;
//
//		// turn on the enhanced debugger
//		// XMPPConnection.DEBUG_ENABLED = true;
//
//		// Enter your login information here
////		c.login("elijah", "123456");
////		c.login("tommy", "123456");
//		c.login("elijah", "123456");
//
//		c.displayBuddyList();
//
//		System.out.println("-----");
//
//		System.out.println("Who do you want to talk to? - Type contacts full email address:");
//		String talkTo = br.readLine();
//
//		System.out.println("-----");
//		System.out.println("All messages will be sent to " + talkTo);
//		System.out.println("Enter your message in the console:");
//		System.out.println("-----\n");
//
//		while (!(msg = br.readLine()).equals("bye")) {
//			c.sendMessage(msg, talkTo);
//		}
//
//		c.disconnect();
//		System.exit(0);
//	}
//	
//	public void tet(){
//		// Create a packet filter to listen for new messages from a particular
//		// user. We use an AndFilter to combine two other filters._
//		PacketFilter filter = new AndFilter(new PacketTypeFilter(Message.class));
//		// Assume we've created a XMPPConnection name "connection".
//
//		// First, register a packet collector using the filter we created.
////		PacketCollector myCollector = 
//		connection.createPacketCollector(filter);
//		// Normally, you'd do something with the collector, like wait for new packets.
//
//		// Next, create a packet listener. We use an anonymous inner class for brevity.
//		PacketListener myListener = new PacketListener() {
//				public void processPacket(Packet packet) {
//					// Do something with the incoming packet here._
//					System.out.println("---------------"+packet.toString());
//				}
//			};
//		// Register the listener._
//		connection.addPacketListener(myListener, filter);
//	}
//
//}