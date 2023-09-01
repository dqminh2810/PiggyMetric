package com.piggymetrics.statistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
public class StatisticServiceApplication {

	public static void main(String[] args) {
//		System.setProperty("java.net.preferIPv4Stack", "true");
		SpringApplication.run(StatisticServiceApplication.class, args);
	}

}
