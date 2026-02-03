package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.dto.NotificationRequest;
import com.microservices.food_delivery.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor

public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(@RequestBody NotificationRequest notificationRequest){

        notificationService.notifyUser(
                notificationRequest.getUserId(),
                notificationRequest.getTitle(),
                notificationRequest.getBody()
        );

        return ResponseEntity.ok("Notification sent successfully");
    }
}
