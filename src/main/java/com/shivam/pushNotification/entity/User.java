package com.shivam.pushNotification.entity;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data 
public class User {
    @Id 
    private Long id;
    private String name;
}
