package com.shivam.pushNotification.entity;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data 
public class Subscription {
    @Id 
    private Long id;

    private Long userId;
    private Long channelId;
}
