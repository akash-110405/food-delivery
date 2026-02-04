package com.microservices.food_delivery.dto;

import lombok.Data;

@Data
public class EmailOtpVerifyRequest {

    private String email;
    private String otp;
}
