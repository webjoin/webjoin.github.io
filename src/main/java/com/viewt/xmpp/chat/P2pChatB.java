package com.viewt.xmpp.chat;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PresenceTypeFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.Roster.SubscriptionMode;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterListener;
import org.jivesoftware.smack.roster.RosterLoadedListener;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

public class P2pChatB {
	
	
	public static void main(String[] args) {
		String username= "jennifer", 
				password= "123456",
				resource = "viewt",
				serviceName = "openfire-test",
				host = "127.0.0.1";
		XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
//				  .setUsernameAndPassword(username, password)
				  .setServiceName(serviceName)
				  .setHost(host)
				  .setPort(5222)
//				  .setDebuggerEnabled(true)
				  .setSecurityMode(SecurityMode.disabled)
				  .build();
//		AbstractXMPPConnection conn2 = new XMPPTCPConnection(username,password,"127.0.0.1");
		final AbstractXMPPConnection conn2 = new XMPPTCPConnection(config);
		try {
//			SASLAuthentication.registerSASLMechanism(new SASLPlainMechanism());
			conn2.connect();
//			conn2.login();
			conn2.login(username, password,resource);
			conn2.addAsyncStanzaListener(new StanzaListener() {
				
				public void processPacket(Stanza packet) throws NotConnectedException {
					System.out.println("subscribe--"+packet.toXML());
					//packet.
					Presence presence = (Presence)packet;
					if(Presence.Type.subscribe == presence.getType()){
//						Presence packet1 = new Presence(Presence.Type.unsubscribe);
						Presence packet1 = new Presence(Presence.Type.subscribe);
						packet1.setTo(presence.getFrom());
						conn2.sendStanza(packet1);
						System.out.println(packet1.toXML());
					}
				}
			}, new StanzaTypeFilter(Presence.class));
			Roster roster = Roster.getInstanceFor(conn2);
			roster.setSubscriptionMode(SubscriptionMode.manual);
			Set<RosterEntry> set  = roster.getEntries();
			Iterator<RosterEntry> iterator =  set.iterator();
			while(iterator.hasNext()){
				System.out.println(iterator.next().getName());
			}
//			roster.createGroup("family");
//			roster.createEntry("elijah@openfire-test", "elijah", new String[]{"family"});
			roster.addRosterLoadedListener(new RosterLoadedListener() {
				public void onRosterLoaded(Roster roster) {
					SubscriptionMode mode = roster.getSubscriptionMode();
					roster.setSubscriptionMode(SubscriptionMode.accept_all);
					System.out.println(mode);
				}
			});
			roster.addRosterListener(new RosterListener() {
				
				public void presenceChanged(Presence presence) {
					System.out.println("Presence changed: " + presence.getFrom() + " " + presence);
				}
				
				public void entriesUpdated(Collection<String> addresses) {
					System.out.println("entriesUpdated");
				}
				
				public void entriesDeleted(Collection<String> addresses) {
					System.out.println("entriesDeleted");
				}
				
				public void entriesAdded(Collection<String> addresses) {
					System.out.println("entriesAdded"+addresses.iterator().next());
				}
			});
//			PacketListener packetListener = new PacketListener() {
//				
//				public void processPacket(Stanza packet) throws NotConnectedException {
//					
//				}
//			};
			StanzaFilter packetFilter = new AndFilter(new StanzaTypeFilter(Message.class));
//			conn2.addPacketListener(packetListener, packetFilter);
			conn2.addAsyncStanzaListener(new StanzaListener() {
				public void processPacket(Stanza packet) throws NotConnectedException {
					System.out.println("addChatListene1--"+packet.toXML());
					Message packet1 = new Message();
					packet1.setFrom(conn2.getUser());
					packet1.setTo(packet.getFrom());
					packet1.setBody("nihao ya ..."+((Message)packet).getBody());
//					conn2.sendStanza(packet1);
				}
			}, packetFilter);
			
			
			ChatManager chatManager = ChatManager.getInstanceFor(conn2);
			chatManager.addChatListener(new ChatManagerListener() {
				public void chatCreated(Chat chat, boolean createdLocally) {
					chat.addMessageListener(new ChatMessageListener() {
						public void processMessage(Chat chat, Message message) {
							System.out.println("addChatListener--"+message.toXML());
						}
					});
				}
			});

		} catch (SmackException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);
		String str = null ;
		while(!"bye".equals(str = sc.next())){
			System.out.println(str);
		}
		sc.close();
		conn2.disconnect();
	}

}
