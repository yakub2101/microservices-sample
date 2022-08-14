package com.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SMS notification service. Sends an SMS notification.
 */
@SpringBootApplication
public class SmsNotificationService {

	public static void main(String[] args) {
		SpringApplication.run(SmsNotificationService.class, args);
	}

}
