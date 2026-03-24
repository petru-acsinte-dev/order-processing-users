package com.orderprocessing.order_processing_users;

import org.springframework.boot.SpringApplication;

public class TestOrderProcessingUsersApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrderProcessingUsersApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
