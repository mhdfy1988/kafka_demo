package com.ly.kafka.config;

import java.util.Map;
import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "kafka")
public class KafkaConfig {
	private Properties producer;
	
	private Map<String,String> consumer;
}
