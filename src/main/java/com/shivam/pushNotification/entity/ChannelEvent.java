package com.shivam.pushNotification.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor 
public class ChannelEvent {
    private Long channelId;
    private String videoId;
    private String videoTitle;
}
