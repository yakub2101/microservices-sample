package com.notifications.repository;

import com.notifications.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface NotificationRepository extends MongoRepository<Notification, Integer> {

    @Query("{type:'?0'}")
    List<Notification> findByType(String type);
}
