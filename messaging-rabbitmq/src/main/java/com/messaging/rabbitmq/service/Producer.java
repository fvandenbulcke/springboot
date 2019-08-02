package com.messaging.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.messaging.rabbitmq.dto.RabbitMqMessage;

@Component
public class Producer {
	
    private final RabbitTemplate rabbitTemplate;
    private final String topicExchangeName;

    public Producer(RabbitTemplate rabbitTemplate, @Value("${broker.topic.exchange.name}") String topicExchangeName) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchangeName = topicExchangeName;
    }
    
    public void produce(String message) {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(topicExchangeName, "foo.bar.baz", new RabbitMqMessage(message));
    }

}
