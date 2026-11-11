package com.shivam.pushNotification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor 
public class NotificationDto {
    private String message;
    private Long channelId;
}
