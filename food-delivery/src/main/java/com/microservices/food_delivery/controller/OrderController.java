package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.dto.OrderRequest;
import com.microservices.food_delivery.entity.Order;
import com.microservices.food_delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order placeOrder(@RequestBody OrderRequest orderRequest, Authentication authentication){

        String email = authentication.getName();
        return orderService.placeOrder(email, orderRequest.getTotalAmount());
    }
    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
}
