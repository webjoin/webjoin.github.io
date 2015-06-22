package com.viewt.kafka.another;

import java.util.Properties;

import kafka.producer.KeyedMessage;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

public class ProducerTest {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.setProperty("metadata.broker.list","192.168.1.130:9098");
		props.setProperty("serializer.class","kafka.serializer.StringEncoder");   
		props.put("request.required.acks","1");   
		ProducerConfig config = new ProducerConfig(props);  
		Producer<String, String>  producer = new Producer<String, String>(config); 
		
		for (int i = 0; i < 10000; i++) {
			KeyedMessage<String, String> data = new KeyedMessage<String, String>("LOG_TEST","aaaaaaaaaaaaaaaaaaaaaaa");   
			producer.send(data); 
		}
	}
}
