package com.ly.kafka;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ly.kafka.service.Consumer;
import com.ly.kafka.service.Producer;

@SpringBootApplication
public class KafkaDemoApplication implements ApplicationRunner{
	
	@Autowired
	private Producer producer;
	
	@Autowired
	private Consumer consumer;
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoApplication.class, args);
	}

//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		producer.init();
//		List<String> msgList = new ArrayList<String>();
//		msgList.add("这是第一条记录");
//		msgList.add("这是第二条记录");
//		producer.sendMsg("test",msgList);
//		
//		List<String> msgList2 = new ArrayList<String>();
//		msgList2.add("这是第3条记录");
//		msgList2.add("这是第4条记录");
//		producer.sendMsg("test",msgList2);
//		
//		producer.close();
//	}
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		try {
			consumer.init("test");
			consumer.listen();
		}finally {
			consumer.close();
		}
	}

}
