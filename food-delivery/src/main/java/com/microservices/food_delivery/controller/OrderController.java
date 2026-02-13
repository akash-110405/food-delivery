package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.dto.ApiResponse;
import com.microservices.food_delivery.dto.OrderRequest;
import com.microservices.food_delivery.entity.Order;
import com.microservices.food_delivery.security.SecurityUtil;
import com.microservices.food_delivery.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse<Order>> placeOrder(@RequestBody OrderRequest orderRequest,
                                                         Authentication authentication) {

        String email = authentication.getName();
        Order placeOrder = orderService.placeOrder(email, orderRequest.getTotalAmount(),
                orderRequest.getFoodId(),
                orderRequest.getQuantity());
        String role = SecurityUtil.getCurrentUserRole();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Order placed successfully",
                        HttpStatus.CONTINUE,
                        role,
                        placeOrder
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Order>>> getAllOrders() {
        List<Order> getAllOrders = orderService.getAllOrders();
        String role = SecurityUtil.getCurrentUserRole();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Orders Listed successfully",
                        HttpStatus.MULTIPLE_CHOICES,
                        role,
                        getAllOrders
                )
        );
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<List<Order>>> getOrderItems(@PathVariable Long orderId) {
        List<Order> getOrderItems = orderService.getItemsByOrder(orderId);
        String role = SecurityUtil.getCurrentUserRole();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Foods fetched successfully",
                        HttpStatus.MULTIPLE_CHOICES,
                        role,
                        getOrderItems
                )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Order>>> getOrderByUserId(@PathVariable Long userId) {

        List<Order> orders = orderService.getOrdersByUserId(userId);
        String role = SecurityUtil.getCurrentUserRole();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Orders fetched by userId",
                        HttpStatus.OK,
                        role,
                        orders
                )
        );
    }
}
