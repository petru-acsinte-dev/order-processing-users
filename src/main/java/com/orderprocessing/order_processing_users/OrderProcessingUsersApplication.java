package com.orderprocessing.order_processing_users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.orderprocessing")
@EnableJpaRepositories(basePackages = "com.orderprocessing.users.repositories")
@EntityScan(basePackages = "com.orderprocessing.users.entities")
public class OrderProcessingUsersApplication {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		SpringApplication.run(OrderProcessingUsersApplication.class, args);
	}

}
