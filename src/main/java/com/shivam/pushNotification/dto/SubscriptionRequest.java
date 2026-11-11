package com.shivam.pushNotification.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionRequest {
    @NotNull(message = "userId is required")
    private Long userId;

    @NotNull(message = "channelId is required")
    private Long channelId;
}
