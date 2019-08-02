package com.messaging.rabbitmq.dto;

public class RabbitMqMessage {
	
	private String label;

	public RabbitMqMessage() {}
	
	public RabbitMqMessage(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
