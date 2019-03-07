package com.kafka.producer.application.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kafka.producer.application.model.Stock;

@EnableKafka
@Configuration
public class ConsumerConfig {

	@Bean
	public ConsumerFactory<String, Stock> consumerFactory(){
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(),new StringDeserializer(), new JsonDeserializer<Stock>(Stock.class));
	}
	
	@Bean
	public Map<String,Object> consumerConfigs(){
		Map<String,Object> properties = new HashMap<>();
		properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:8080");
		properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, "17");
		properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return properties;
	}
	
	@Bean(name="myKafkaListenerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, Stock> kafkaListenerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, Stock> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	
}
