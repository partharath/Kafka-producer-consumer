package com.kafka.producer.application.producer;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.producer.application.model.Stock;

@RestController
@RequestMapping("stock")
public class StockProducer {

	@Autowired
	private KafkaTemplate<String, Stock> stockTemplate;

	@PostMapping(value="/data",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String postStockData(@RequestBody Stock data) throws InterruptedException, ExecutionException {
		ListenableFuture<SendResult<String, Stock>> future = stockTemplate.send("stock-data", data);
		if (future.get().getRecordMetadata() != null) {
			return "Success";
		} else
			return "Failure";

	}

	@KafkaListener(topics = "stock-data", groupId = "17", containerFactory = "myKafkaListenerFactory")
	public void retrieveStock(@Payload Stock data) {
		System.out.println(data.getStockName());
		System.out.println(data.getStockValue());

	}
}
