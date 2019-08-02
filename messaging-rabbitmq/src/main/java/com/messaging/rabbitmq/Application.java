package com.messaging.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws InterruptedException {
//        SpringApplication.run(Application.class, args).close();
        SpringApplication.run(Application.class, args);
    }

}