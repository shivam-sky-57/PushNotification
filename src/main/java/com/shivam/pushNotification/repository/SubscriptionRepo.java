package com.shivam.pushNotification.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.shivam.pushNotification.entity.Subscription;

public interface SubscriptionRepo extends MongoRepository<Subscription, String> {
    List<Subscription> findByChannelId(Long channelId);
    List<Subscription> findByUserId(Long userId);
    boolean existsByChannelIdAndUserId(Long channelId, Long userId);
    void deleteByChannelIdAndUserId(Long channelId, Long userId);
}
