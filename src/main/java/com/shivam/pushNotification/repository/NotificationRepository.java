package com.shivam.pushNotification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shivam.pushNotification.entity.Notification;

public interface NotificationRepository extends MongoRepository<Notification, Long> {
    
}
