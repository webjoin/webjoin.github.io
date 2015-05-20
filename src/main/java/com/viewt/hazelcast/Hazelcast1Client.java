package com.viewt.hazelcast;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class Hazelcast1Client {
	
	public static void main(String[] args) {
		
		
		ClientConfig clientConfig = new ClientConfig();
//		clientConfig.set
		HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
		IMap map = client.getMap("customers");
		System.out.println("Map Size : "+map.size());
		client.shutdown();
		System.exit(0);
	}

}
