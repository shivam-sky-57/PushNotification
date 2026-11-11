package com.shivam.pushNotification.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "channels")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Channel {
    @Id 
    private String id;
    private Long channelId;
    private String name;

    public Channel(Long channelId, String name) {
        this.channelId = channelId;
        this.name = name;
    }
}
