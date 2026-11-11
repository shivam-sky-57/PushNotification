package com.shivam.pushNotification.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shivam.pushNotification.dto.SubscriptionRequest;
import com.shivam.pushNotification.entity.Subscription;
import com.shivam.pushNotification.repository.SubscriptionRepo;

@Service 
public class SubscriptionService {
    private static final Logger log = LoggerFactory.getLogger(SubscriptionService.class);

    private final SubscriptionRepo subscriptionRepo;

    public SubscriptionService(SubscriptionRepo subscriptionRepo) {
        this.subscriptionRepo = subscriptionRepo;
    }

    public boolean subscribe(SubscriptionRequest request) {
        if (subscriptionRepo.existsByChannelIdAndUserId(request.getChannelId(), request.getUserId())) {
            log.info("User {} is already subscribed to channel {}", request.getUserId(), request.getChannelId());
            return false;
        }

        Subscription sub = new Subscription(request.getUserId(), request.getChannelId());
        subscriptionRepo.save(sub);
        log.info("User {} subscribed to channel {}", request.getUserId(), request.getChannelId());
        return true;
    }

    public boolean unsubscribe(SubscriptionRequest request) {
        if (!subscriptionRepo.existsByChannelIdAndUserId(request.getChannelId(), request.getUserId())) {
            log.info("Subscription not found for user {} on channel {}", request.getUserId(), request.getChannelId());
            return false;
        }

        subscriptionRepo.deleteByChannelIdAndUserId(request.getChannelId(), request.getUserId());
        log.info("User {} unsubscribed from channel {}", request.getUserId(), request.getChannelId());
        return true;
    }

    public List<Long> getSubscribers(Long channelId) {
        return subscriptionRepo.findByChannelId(channelId)
                .stream()
                .map(Subscription::getUserId)
                .toList();
    }
}
