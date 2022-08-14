package com.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Email notification service. Sends an email notification.
 */
@SpringBootApplication
public class EmailNotificationService {

	public static void main(String[] args) {
		SpringApplication.run(EmailNotificationService.class, args);
	}

}
