package com.shivam.pushNotification.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.shivam.pushNotification.dto.NotificationDto;
import com.shivam.pushNotification.entity.Notification;
import com.shivam.pushNotification.repository.NotificationRepository;

@Service 
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationRepository notificationRepo;

    public NotificationService(SimpMessagingTemplate messagingTemplate, NotificationRepository notificationRepo) {
        this.messagingTemplate = messagingTemplate;
        this.notificationRepo = notificationRepo;
    }

    public void sendNotification(Long userId, NotificationDto dto) {
        log.info("Sending notification to userId {}: {}", userId, dto.getMessage());

        // Persist notification in MongoDB
        Notification notification = new Notification(userId, dto.getChannelId(), dto.getMessage(), true);
        notificationRepo.save(notification);

        // Push message via WebSocket to user's private queue
        messagingTemplate.convertAndSendToUser(
            userId.toString(),
            "/queue/notifications",
            dto
        );
    }

    public void broadcastToSubscribers(List<Long> userIds, NotificationDto dto) {
        for (Long userId : userIds) {
            sendNotification(userId, dto);
        }
    }
}
