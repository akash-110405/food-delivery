package com.microservices.food_delivery.dto;

import lombok.Data;

@Data
public class NotificationRequest {

    private String token;
    private Long userId;
    private String title;
    private String body;
}
