package com.shivam.pushNotification.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "notifications")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id 
    private String id;

    private Long userId;
    private Long channelId;
    private String message;
    private boolean delivered;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Notification(Long userId, Long channelId, String message, boolean delivered) {
        this.userId = userId;
        this.channelId = channelId;
        this.message = message;
        this.delivered = delivered;
        this.createdAt = LocalDateTime.now();
    }
}
