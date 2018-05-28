package com.service_exchange.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.service_exchange.api_services"})
@EnableJpaRepositories({"com.service_exchange.api_services.dao"})
@EntityScan("com.service_exchange.entities")
public class ServiceExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceExchangeApplication.class, args);
	}
}