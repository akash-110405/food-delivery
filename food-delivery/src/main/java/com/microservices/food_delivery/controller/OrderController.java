package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.entity.Order;
import com.microservices.food_delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order placeOrder(@RequestParam Double totalAmount, Authentication authentication){

        String email = authentication.getName();
        return orderService.placeOrder(email, totalAmount);
    }
}
