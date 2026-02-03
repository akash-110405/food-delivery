package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.dto.FcmTokenRequest;
import com.microservices.food_delivery.entity.User;
import com.microservices.food_delivery.service.FcmTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fcm")
@RequiredArgsConstructor
public class FcmController {

    private final FcmTokenService fcmTokenService;

    @PostMapping("/register")
    public ResponseEntity<String> registerToken(
            @RequestBody FcmTokenRequest fcmTokenRequest,
            Authentication authentication) {

        String token = fcmTokenRequest.getFcmToken();
        String email = authentication.getName(); // JWT subject

        fcmTokenService.saveToken(token, email);

        return ResponseEntity.ok("FCM token registered successfully");
    }
}


