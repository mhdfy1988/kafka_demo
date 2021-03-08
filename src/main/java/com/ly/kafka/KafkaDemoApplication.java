package com.ly.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ly.kafka.service.Producer;

@SpringBootApplication
public class KafkaDemoApplication implements ApplicationRunner{
	
	@Autowired
	private Producer producer;
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		producer.sendMsg();
	}

}
