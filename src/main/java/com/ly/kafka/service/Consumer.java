package com.ly.kafka.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.kafka.config.KafkaConfig;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Consumer {
	@Autowired
	private KafkaConfig config;
	
	private KafkaConsumer<String, String> consumer = null;
	
	public void init (String topic) {
		Properties props = config.getConsumer();
		consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList(topic));
	}
	
	public void init (List<String> topics) {
		Properties props = config.getConsumer();
		consumer = new KafkaConsumer<>(props);
		consumer.subscribe(topics);
	}
	
	public  void  listen() {
		while (true) {
			ConsumerRecords<String,String>  records = consumer.poll(Duration.ofMillis(500));
			log.info("size:"+records.count());
			records.forEach((ConsumerRecord<String,String> record) -> {
				log.info(record.value());
			});
		}
	}
	
	
	public void close () {
		consumer.close();
	}

}
