package com.viewt.hazelcast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Queue;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class Hazelcast1Server {
	
	public static void main(String[] args) throws Exception {
		
		HazelcastInstance hazelcastInstance =	Hazelcast.newHazelcastInstance();
		Map<Integer,String> customers = hazelcastInstance.getMap("customers");
		customers.put(1, "Joe");
		customers.put(2, "Ali");
		customers.put(3, "Avi");
		
		System.out.println("Customer with key 1:"+customers.get(1));
		System.out.println("Map Size:"+customers.size());
		
		Queue<String> queuecustomers = hazelcastInstance.getQueue("customers");
		queuecustomers.offer("Tom");
		queuecustomers.offer("Mary");
		queuecustomers.offer("Jane");
		
		System.out.println("First customer: "+queuecustomers.poll());
		System.out.println("Second customer: "+queuecustomers.peek());
		System.out.println("Queue size: "+queuecustomers.size());
		
		
//		hazelcastInstance.get
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String msg;
		
		System.out.println("-----");
		System.out.println("Enter your message in the console:");
		System.out.println("-----\n");

		while (!(msg = br.readLine()).equals("bye")) {
			Integer int1 = Integer.valueOf(msg);
			for (int i = 0; i < 100; i++) {
				customers.put(3+i+int1, "Avi"+i+msg);
				queuecustomers.offer("Avi"+i+msg);
			}
			System.out.println(msg);
		}

		hazelcastInstance.shutdown();
		System.exit(0);
		
		
		
	}

}
