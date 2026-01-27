package com.microservices.food_delivery.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public String handleException(Exception e){
        return e.getMessage();
    }
}
