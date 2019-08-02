package com.messaging.rabbitmq.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.messaging.rabbitmq.service.Producer;

@RestController
@RequestMapping(path="/message")
public class MessageController {
	
	private Producer producer;
	
	public MessageController(Producer producer) {
		this.producer = producer;
	}
	
	@PostMapping(path="/produce/{message}")
	public ResponseEntity<?> produceMessage(@PathVariable(name="message") String message) {
		producer.produce(message);
		return new ResponseEntity<Boolean>(HttpStatus.CREATED);
	}
	
}
