package com.shivam.pushNotification.entity;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data 
public class Notification {
    @Id 
    private Long id;

    private Long userId;
    private String message;
    private boolean delivered;
}
