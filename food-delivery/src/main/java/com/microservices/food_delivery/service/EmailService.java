package com.microservices.food_delivery.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendOtp(String email, String otp){
        System.out.println("EMAIL OTP to " + email + ": " + otp);
    }
}
