package com.forex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.aws.context.config.annotation.EnableContextCredentials;
import org.springframework.cloud.aws.context.config.annotation.EnableContextRegion;
import org.springframework.cloud.aws.jdbc.config.annotation.EnableRdsInstance;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"com.forex", "com.forexbackend", "com.web"})
@EnableAutoConfiguration
@EnableRdsInstance(databaseName="******", dbInstanceIdentifier="******", username="******",password="******")
@EnableContextCredentials(accessKey="******", secretKey="******")
@EnableContextRegion(region="******")
public class ForexInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForexInfoApplication.class, args);
	}
}
