package com.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Push notification service. Sends a push notification.
 */
@SpringBootApplication
public class PushNotificationService {

	public static void main(String[] args) {
		SpringApplication.run(PushNotificationService.class, args);
	}

}
