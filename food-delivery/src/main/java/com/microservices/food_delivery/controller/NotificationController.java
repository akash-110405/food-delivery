package com.microservices.food_delivery.controller;

import com.google.firebase.messaging.Notification;
import com.microservices.food_delivery.dto.ApiResponse;
import com.microservices.food_delivery.dto.NotificationRequest;
import com.microservices.food_delivery.security.SecurityUtil;
import com.microservices.food_delivery.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor

public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<?>> sendNotification(
            @RequestBody NotificationRequest notificationRequest){

         notificationService.notifyUser(
                notificationRequest.getUserId(),
                notificationRequest.getTitle(),
                notificationRequest.getBody()
        );

        String role = SecurityUtil.getCurrentUserRole();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Notification sent successfully",
                        HttpStatus.OK,
                        role,
                        null
                )
        );
    }
}
