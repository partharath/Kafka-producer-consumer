package com.kafka.producer.application.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.kafka.producer.application.model.Stock;
import com.kafka.producer.application.partitioner.CustomPartitioner;

@SpringBootConfiguration
public class ProducerConfig {
	
	@Bean
	public ProducerFactory<String, Stock> producerFactory(){
		JsonSerializer<Stock> stockSerializer = new JsonSerializer<Stock>();
		return new DefaultKafkaProducerFactory<>(config(),new StringSerializer() ,stockSerializer);
	}
	

	@Bean
	public Map<String,Object> config(){
		Map<String,Object> properties = new HashMap<>();
		properties.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:8080");
		properties.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		properties.put(org.apache.kafka.clients.producer.ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartitioner.class);
		return properties;
	}
	
	@Bean
	public KafkaTemplate<String,Stock> stockKafka(){
		return new KafkaTemplate<>(producerFactory());
	}

}
