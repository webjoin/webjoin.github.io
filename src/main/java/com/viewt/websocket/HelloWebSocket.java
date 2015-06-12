package com.viewt.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.sun.jmx.snmp.Timestamp;

@ServerEndpoint(value = "/websocket")
public class HelloWebSocket {
	public HelloWebSocket(){}
	static Set<HelloWebSocket> set = new HashSet<HelloWebSocket>();
	Session session1 = null;  
//	@OnMessage
//    public void onMessage(String session,String cc)   
//        throws IOException, InterruptedException {  
//		System.out.println("Exception...");
//    }
	@OnMessage
    public void onMessage(String message, Session session)   
        throws IOException, InterruptedException {  
		if( "".equals(message.trim())){
			System.out.println("heartbeat-------------"+session.getId());
			return ;
		}
        // Print the client message for testing purposes  
        System.out.println("Received: " + message);     
        // Send the first message to the client  
        session.getBasicRemote().sendText("This is the first server message");  
          
        // Send 3 messages to the client every 5 seconds  
//        int sentMessages = 0;  
//        while(sentMessages < 3){  
//            Thread.sleep(5000);  
//            session.getBasicRemote().sendText("This is an intermediate server message. Count: "+ sentMessages);  
//            sentMessages++;  
//        }
        // Send a final message to the client  
        session.getBasicRemote().sendText("This is the last server message--"+message);
		
        if(message.contains("broadcast")){
        	Iterator<HelloWebSocket> iterator = set.iterator();
        	while(iterator.hasNext()){
        		Session session1 = iterator.next().session1;
        		if(session1.isOpen())
        			session1.getBasicRemote().sendText(message);
        	}
        }
		
    }
      
    @OnOpen  
    public void onOpen (Session session) {
    	this.session1 = session;
    	set.add(this);
    	RemoteEndpoint.Async async = session.getAsyncRemote();
    	RemoteEndpoint.Basic base = session.getBasicRemote();
        System.out.println("Client connected------"+this+"-------"+set.size()+"---"+this.session1.getId());  
    }  
  
    @OnClose  
    public void onClose (Session session) {
        System.out.println("Connection closed");  
        set.remove(session);
    }  

    @OnError
    public void onError(Session session, Throwable error){
    	set.remove(session);
    	 System.out.println("发生错误");
         error.printStackTrace();
    }
    
    

}
