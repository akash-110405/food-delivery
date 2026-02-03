package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.entity.User;
import com.microservices.food_delivery.service.FcmTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fcm")
@RequiredArgsConstructor
public class FcmController {

    private final FcmTokenService service;

    @PostMapping("/register")
    public ResponseEntity<String> registerToken(
            @RequestParam String token,
            Authentication authentication
    ) {
        User user = (User) authentication.getPrincipal();
        service.saveToken(token, user.getId());
        return ResponseEntity.ok("FCM token saved");
    }
}

