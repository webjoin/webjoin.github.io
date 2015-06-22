package com.viewt.kafka.another;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class ConSumerTest {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("zookeeper.connect", "192.168.1.130:2181");
		props.put("group.id", "YTO_GROUP1");
		props.put("zookeeper.session.timeout.ms","10000");   
		props.put("auto.commit.enable", "true");    
		props.put("auto.commit.interval.ms", 30*1000+"");
		ConsumerConfig config = new ConsumerConfig(props);
		ConsumerConnector consumer =kafka.consumer.Consumer.createJavaConsumerConnector(config); 
		Map<String,Integer> topickMap = new HashMap<String, Integer>();   
		topickMap.put("LOG_TEST", 1);   
		Map<String, List<KafkaStream<byte[],byte[]>>>  streamMap =consumer.createMessageStreams(topickMap);   
		KafkaStream<byte[],byte[]>stream = streamMap.get("LOG_TEST").get(0);   
		ConsumerIterator<byte[],byte[]> it =stream.iterator();   
		System.out.println("消费开始:-------------------");
		while(true){   
			if(it.hasNext()){
				try {
					String json = new String(it.next().message());
					System.out.println(json);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}
}
