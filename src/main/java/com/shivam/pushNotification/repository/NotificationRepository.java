package com.shivam.pushNotification.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shivam.pushNotification.entity.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByUserId(Long userId);
    List<Notification> findByChannelId(Long channelId);
}
