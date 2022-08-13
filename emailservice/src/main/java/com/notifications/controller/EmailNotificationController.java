package com.notifications.controller;

import com.notifications.model.Notification;
import com.notifications.repository.NotificationRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/email")
class EmailNotificationController {

  private final NotificationRepository repository;

  private final String type ="email";

  EmailNotificationController(NotificationRepository repository) {

    this.repository = repository;
  }


  @GetMapping("/readall")
  List<Notification> getAllEmailNotifications() {

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

    //"Send" notification
    System.out.println("Sending email notification...");
    System.out.println(notification.getText());

    return result;
  }
}