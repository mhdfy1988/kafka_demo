package com.ly.kafka.service;

import java.util.List;
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
	
	private KafkaProducer<String, String> producer = null;
	
	public void init () {
		Properties  props = config.getProducer();
		producer = new KafkaProducer<>(props);
	}
	
	public void sendMsg(String topic,List<String> msgList) {
		for(String msg : msgList) {
			producer.send(new ProducerRecord<String, String>(topic, msg));
		}
	}
	
	public void close () {
		producer.close();
	}
}       	