package com.alhas.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NotoficationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotoficationApplication.class, args);
	}

}
