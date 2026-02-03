package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.dto.LoginRequest;
import com.microservices.food_delivery.dto.RegisterRequest;
import com.microservices.food_delivery.entity.User;
import com.microservices.food_delivery.repository.UserRepository;
import com.microservices.food_delivery.security.JwtUtil;
import com.microservices.food_delivery.service.UserService;
import lombok.Data;
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
@Data
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final UserRepository userRepository;

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

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setTokenVersion(user.getTokenVersion() + 1);
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(),user.getTokenVersion());
        return Map.of("token", token);
    }



}