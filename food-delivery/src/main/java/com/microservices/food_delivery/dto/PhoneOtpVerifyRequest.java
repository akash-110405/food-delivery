package com.microservices.food_delivery.dto;

import lombok.Data;

@Data
public class PhoneOtpVerifyRequest {

    private String phoneNumber;
    private String otp;
}
