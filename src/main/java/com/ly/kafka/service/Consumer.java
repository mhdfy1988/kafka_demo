package com.ly.kafka.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
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
	
	/**
	 * 每次从最新拉取数据（场景：间隔消费 每次消费取最新）
	 * @param topic
	 */
	public void initLastestOffset (String topic) {
		Properties props = config.getConsumer();
		consumer = new KafkaConsumer<>(props);
		
		List<TopicPartition> tps = new ArrayList<TopicPartition>();
		List<PartitionInfo>  partitionInfos = consumer.partitionsFor(topic);
		partitionInfos.forEach(action -> {
			tps.add(new TopicPartition(topic, action.partition()));
		});
        consumer.assign(tps);
        consumer.seekToEnd(tps);
	}
	
	/**
	 * 持续监听消费
	 */
	public  void  listen() {
		while (true) {
			ConsumerRecords<String,String>  records = consumer.poll(Duration.ofMillis(500));
			log.info("size:"+records.count());
			records.forEach((ConsumerRecord<String,String> record) -> {
				log.info(record.value());
			});
		}
	}
	
	/**
	 * 只消费一次
	 * @return
	 */
	public  ConsumerRecords getRecords() {
		ConsumerRecords<String,String>  records = consumer.poll(Duration.ofMillis(500));
		return records;
	}
	
	public void close () {
		consumer.close();
	}

}
