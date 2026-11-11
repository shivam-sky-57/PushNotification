package com.shivam.pushNotification.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor 
public class ChannelEvent {
    @NotNull(message = "channelId is required")
    private Long channelId;

    private String videoId;

    @NotBlank(message = "videoTitle is required")
    private String videoTitle;
}
