package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.dto.LoginRequest;
import com.microservices.food_delivery.dto.RegisterRequest;
import com.microservices.food_delivery.security.JwtUtil;
import com.microservices.food_delivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request){
        System.out.println("NAME = " + request.getName());
        System.out.println("EMAIL = " + request.getEmail());
        System.out.println("PASSWORD = " + request.getPassword());

        userService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public Map<String,String> login(@RequestBody LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtUtil.generateToken(request.getEmail());
        return Map.of("token", token);
    }
}