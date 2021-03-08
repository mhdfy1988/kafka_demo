package com.ly.kafka.service;

import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ly.kafka.config.KafkaConfig;

@Service
public class Producer {
	
	@Autowired
	private KafkaConfig config;
	
	public void sendMsg() {
		
		Properties  props = config.getProducer();

		KafkaProducer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 0; i < 10; i++) {
		    // 三个参数分别为topic, key,value，send()是异步的，添加到缓冲区立即返回，更高效。
		    producer.send(new ProducerRecord<String, String>("test", "value"+i));
		}
		producer.close();
	}
}       	