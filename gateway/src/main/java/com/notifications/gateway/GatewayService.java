package com.notifications.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Gateway service, redirects requests to notification services. Gets their details from Eureka service.
 * This is a bootstrap class. application.yml is used for routes configuration.
 */
@SpringBootApplication
public class GatewayService {

	public static void main(String[] args) {
		SpringApplication.run(GatewayService.class, args);
	}

}
