package com.messaging.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.messaging.rabbitmq.dto.RabbitMqMessage;

@Component
public class Receiver {

    @RabbitListener(queues="${broker.queue.name}")
    public void receiveMessage(RabbitMqMessage message) {
        System.out.println("Received <" + message.getLabel() + ">");
    }

}