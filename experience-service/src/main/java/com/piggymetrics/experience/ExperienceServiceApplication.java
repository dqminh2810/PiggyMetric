package com.piggymetrics.experience;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
@EnableFeignClients
public class ExperienceServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ExperienceServiceApplication.class, args);
	}

}
