package com.shivam.pushNotification.dto;

import lombok.Data;

@Data 
public class SubscriptionRequest {
    private Long userId;
    private Long channelId;
}
