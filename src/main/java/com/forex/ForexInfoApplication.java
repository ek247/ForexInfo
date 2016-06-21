package com.forex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.forex", "com.forexbackend", "com.web"})
@EnableAutoConfiguration
public class ForexInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForexInfoApplication.class, args);
	}
}
