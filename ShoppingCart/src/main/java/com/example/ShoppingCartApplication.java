package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingCartApplication {

	static final Logger logger = LogManager.getLogger(ShoppingCartApplication.class.getName());
	
	public static void main(String[] args) {
		logger.info("Entered application");
		SpringApplication.run(ShoppingCartApplication.class, args);
	}
}
