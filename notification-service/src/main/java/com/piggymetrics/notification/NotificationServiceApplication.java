package com.piggymetrics.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication()
@EnableFeignClients()
public class NotificationServiceApplication {
	public static void main(String[] args) {
//		System.setProperty("java.net.preferIPv4Stack", "true");
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
