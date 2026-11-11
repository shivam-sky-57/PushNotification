package com.shivam.pushNotification.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "subscriptions")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    @Id 
    private String id;

    private Long userId;
    private Long channelId;

    public Subscription(Long userId, Long channelId) {
        this.userId = userId;
        this.channelId = channelId;
    }
}
