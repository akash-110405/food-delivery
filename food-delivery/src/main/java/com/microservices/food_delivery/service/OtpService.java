package com.microservices.food_delivery.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    public String generateOtp() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    public LocalDateTime expiryTime(){
        return LocalDateTime.now().plusMinutes(5);
    }
}
