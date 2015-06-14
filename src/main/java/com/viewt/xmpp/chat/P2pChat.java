package com.viewt.xmpp.chat;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterListener;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

public class P2pChat {
	
	
	public static void main(String[] args) {
		String username= "elijah", 
				password= "123456",
				resource = "viewt",
				serviceName = "openfire-test",
				host = "192.168.1.117";
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
		AbstractXMPPConnection conn2 = new XMPPTCPConnection(config);
		try {
//			SASLAuthentication.registerSASLMechanism(new SASLPlainMechanism());
			conn2.connect();
//			conn2.login();
			conn2.login(username, password,resource);
			
			final Roster roster = Roster.getInstanceFor(conn2);
			
			Set<RosterEntry> set  = roster.getEntries();
			Iterator<RosterEntry> iterator =  set.iterator();
			while(iterator.hasNext()){
				System.out.println(iterator.next().getName());
			}
//			roster.createGroup("family");
//			roster.createEntry("elijah@openfire-test", "elijah", new String[]{"family"});
			roster.setSubscriptionMode(Roster.SubscriptionMode.reject_all);
//			roster.
			roster.addRosterListener(new RosterListener() {
				
				public void presenceChanged(Presence presence) {
					System.out.println("Presence changed: " + presence.getFrom() + " " + presence);
				}
				
				public void entriesUpdated(Collection<String> addresses) {
					System.out.println("Presence changed: entriesUpdated");
				}
				
				public void entriesDeleted(Collection<String> addresses) {
					System.out.println("Presence changed: entriesDeleted");
				}
				
				public void entriesAdded(Collection<String> addresses) {
					System.out.println("Presence changed: "+addresses.iterator().next());
					//Roster.SubscriptionMode.reject_all;
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
