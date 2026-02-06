package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.dto.ApiResponse;
import com.microservices.food_delivery.dto.CartRequest;
import com.microservices.food_delivery.entity.Cart;
import com.microservices.food_delivery.security.SecurityUtil;
import com.microservices.food_delivery.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<ApiResponse<Cart>> addToCart(@RequestBody CartRequest cartRequest,
                                                     Authentication authentication) {

        String email = authentication.getName();
        Cart addToCart = cartService.addToCart(email, cartRequest.getFoodId(), cartRequest.getQuantity());
        String role = SecurityUtil.getCurrentUserRole();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Cart added successfully",
                        HttpStatus.CREATED,
                        role,
                        addToCart
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Cart>>> viewCart(Authentication authentication) {
        List<Cart> viewCart =  cartService.getUserCart(authentication.getName());
        String role = SecurityUtil.getCurrentUserRole();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Foods fetched successfully",
                        HttpStatus.MULTIPLE_CHOICES,
                        role,
                        viewCart
                )
        );
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<ApiResponse<String>> removeItem(@PathVariable Long cartId) {
        String role = SecurityUtil.getCurrentUserRole();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Cart Removed",
                        HttpStatus.GONE,
                        role,
                        null
                )
        );
    }
}



