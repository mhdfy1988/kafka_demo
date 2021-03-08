package com.ly.kafka.service;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	
	
	public Properties  initConfig() {
		
		
		return null;
	}
	
	public void sendMsg() {
		Properties props = new Properties();
		//该地址是集群的子集，用来探测集群。
		props.put("bootstrap.servers","8.133.186.237:9092");
		// 记录完整提交，最慢的但是最大可能的持久化
		props.put("acks", "all");
		// 请求失败重试的次数
		props.put("retries", 3);
		// batch的大小
		props.put("batch.size", 16384);
		//默认情况即使缓冲区有剩余的空间，也会立即发送请求，设置一段时间用来等待从而将缓冲区填的更多，单位为毫秒，
		//producer发送数据会延迟1ms，可以减少发送到kafka服务器的请求数据
		props.put("linger.ms", 1);
		// 提供给生产者缓冲内存总量
		props.put("buffer.memory", 33554432);
		// 序列化的方式， // ByteArraySerializer或者StringSerializer
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

		KafkaProducer<String, String> producer = new KafkaProducer<>(props);
		
		for (int i = 0; i < 10; i++) {
		    // 三个参数分别为topic, key,value，send()是异步的，添加到缓冲区立即返回，更高效。
		    producer.send(new ProducerRecord<String, String>("test", "value"+i));
		}
		producer.close();
	}
}       	