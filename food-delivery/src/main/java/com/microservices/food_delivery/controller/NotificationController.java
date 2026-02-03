package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor

public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(@RequestParam Long userId,
                                              @RequestParam String title,
                                              @RequestParam String body){

        notificationService.notifyUser(userId,title,body);
        return ResponseEntity.ok("Notification sent successfully");
    }
}
