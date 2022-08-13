package com.notifications.controller;

import com.notifications.model.Notification;
import com.notifications.repository.NotificationRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/push")
class PushNotificationController {

  private final NotificationRepository repository;

  private final String type ="push";

  PushNotificationController(NotificationRepository repository) {

    this.repository = repository;
  }



  @GetMapping("/readall")
  List<Notification> getAllPushNotifications() {

    return repository.findByType(type);
  }

  @PostMapping("/send")
  Notification sendNotification(@RequestBody Notification notification) {

    //Set the notification type
    notification.setType(type);
    //Set current date
    notification.setDate(LocalDate.now());

    //Save to DB
    Notification result = repository.save(notification);

    //Then "Send"
    System.out.println("Sending push notification...");
    System.out.println(notification.getText());

    return result;
  }

}