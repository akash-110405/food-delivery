package com.microservices.food_delivery.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String name;
    private String email;
    private String phoneNumber;
    private String password;

}