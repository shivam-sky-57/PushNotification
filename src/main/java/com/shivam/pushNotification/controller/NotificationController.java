package com.shivam.pushNotification.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.pushNotification.dto.NotificationDto;
import com.shivam.pushNotification.entity.ChannelEvent;
import com.shivam.pushNotification.entity.Notification;
import com.shivam.pushNotification.repository.NotificationRepository;
import com.shivam.pushNotification.service.NotificationService;
import com.shivam.pushNotification.service.SubscriptionService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    private final SubscriptionService subscriptionService;
    private final NotificationRepository notificationRepository;

    public NotificationController(NotificationService notificationService,
                                  SubscriptionService subscriptionService,
                                  NotificationRepository notificationRepository) {
        this.notificationService = notificationService;
        this.subscriptionService = subscriptionService;
        this.notificationRepository = notificationRepository;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@Valid @RequestBody ChannelEvent event) {
        List<Long> subscribers = subscriptionService.getSubscribers(event.getChannelId());

        NotificationDto dto = new NotificationDto(
                "New video: " + event.getVideoTitle(),
                event.getChannelId()
        );

        notificationService.broadcastToSubscribers(subscribers, dto);
        return ResponseEntity.ok("Notification sent to " + subscribers.size() + " subscribers.");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
        List<Notification> notifications = notificationRepository.findByUserId(userId);
        return ResponseEntity.ok(notifications);
    }
}
