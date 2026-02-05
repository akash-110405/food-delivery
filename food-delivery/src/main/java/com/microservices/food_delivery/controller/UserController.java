package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public String profile(){
        return "This is a secured user profile";
    }
}