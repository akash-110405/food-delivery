package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.dto.*;
import com.microservices.food_delivery.service.AuthService;
import com.microservices.food_delivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

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
                request.getPassword()
        );
        return "Registered successfully";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        String token = authService.loginWithEmailPassword(
                request.getEmail(),
                request.getPassword()
        );
        return Map.of("token", token);
    }


    @PostMapping("/email/request-otp")
    public String requestEmailOtp(@RequestBody EmailOtpRequest request) {
        return authService.requestEmailOtp(request.getEmail());
    }

    @PostMapping("/email/verify-otp")
    public Map<String, String> verifyEmailOtp(
            @RequestBody EmailOtpVerifyRequest request) {

        String token = authService.verifyEmailOtp(
                request.getEmail(),
                request.getOtp()
        );
        return Map.of("token", token);
    }

    @PostMapping("/phone/request-otp")
    public String requestPhoneOtp(@RequestBody PhoneOtpRequest request) {
        return authService.requestPhoneOtp(request.getPhoneNumber());
    }

    @PostMapping("/phone/verify-otp")
    public Map<String, String> verifyPhoneOtp(
            @RequestBody PhoneOtpVerifyRequest request) {

        String token = authService.verifyPhoneOtp(
                request.getPhoneNumber(),
                request.getOtp()
        );
        return Map.of("token", token);
    }
}