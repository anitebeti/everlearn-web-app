package com.everlearn.everlearnwebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.everlearn.everlearnwebapp.entity")
public class EverlearnWebApp {

	public static void main(String[] args) {
		SpringApplication.run(EverlearnWebApp.class, args);
	}

}
