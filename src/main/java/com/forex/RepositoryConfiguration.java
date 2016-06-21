package com.forex;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Config class to tell spring where my repository + entities are and some other info
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.forexbackend"})
@EnableJpaRepositories(basePackages = {"com.forexbackend"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
