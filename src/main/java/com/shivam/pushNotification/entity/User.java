package com.shivam.pushNotification.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id 
    private String id;
    private Long userId;
    private String name;

    public User(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
