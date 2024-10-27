package com.example.amazon_agency_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class AmazonAgencyTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazonAgencyTaskApplication.class, args);
	}

}
