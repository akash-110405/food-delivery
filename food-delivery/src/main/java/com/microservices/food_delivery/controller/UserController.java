package com.microservices.food_delivery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.CREATED)
    public String profile(){
        return "This is a secured user profile";
    }

}
