package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.dto.*;
import com.microservices.food_delivery.service.AuthService;
import com.microservices.food_delivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        userService.register(
                request.getName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getPassword()
        );
        return "Registered successfully";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.loginWithEmailPassword(
                request.getEmail(),
                request.getPassword()
        );
        return ResponseEntity.ok(response);
    }


    @PostMapping("/email/request-otp")
    public String requestEmailOtp(@RequestBody EmailOtpRequest request) {
        return authService.requestEmailOtp(request.getEmail());
    }

    @PostMapping("/email/verify-otp")
    public ResponseEntity<AuthResponse> verifyEmailOtp(
            @RequestBody EmailOtpVerifyRequest request) {

        AuthResponse response = authService.verifyEmailOtp(
                request.getEmail(),
                request.getOtp()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/phone/request-otp")
    public String requestPhoneOtp(@RequestBody PhoneOtpRequest request) {
        return authService.requestPhoneOtp(request.getPhoneNumber());
    }

    @PostMapping("/phone/verify-otp")
    public ResponseEntity<AuthResponse> verifyPhoneOtp(
            @RequestBody PhoneOtpVerifyRequest request) {

        AuthResponse response = authService.verifyPhoneOtp(
                request.getPhoneNumber(),
                request.getOtp()
        );
        return ResponseEntity.ok(response);
    }
}