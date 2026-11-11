package com.shivam.pushNotification.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.pushNotification.dto.SubscriptionRequest;
import com.shivam.pushNotification.service.SubscriptionService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@Valid @RequestBody SubscriptionRequest request) {
        boolean created = subscriptionService.subscribe(request);
        if (created) {
            return ResponseEntity.ok("User " + request.getUserId() + " subscribed to channel " + request.getChannelId());
        }
        return ResponseEntity.ok("User " + request.getUserId() + " is already subscribed to channel " + request.getChannelId());
    }

    @PostMapping("/unsubscribe")
    public ResponseEntity<String> unsubscribe(@Valid @RequestBody SubscriptionRequest request) {
        boolean removed = subscriptionService.unsubscribe(request);
        if (removed) {
            return ResponseEntity.ok("User " + request.getUserId() + " unsubscribed from channel " + request.getChannelId());
        }
        return ResponseEntity.badRequest().body("Subscription not found for user " + request.getUserId() + " and channel " + request.getChannelId());
    }

    @GetMapping("/channel/{channelId}")
    public ResponseEntity<List<Long>> getSubscribers(@PathVariable Long channelId) {
        return ResponseEntity.ok(subscriptionService.getSubscribers(channelId));
    }
}
