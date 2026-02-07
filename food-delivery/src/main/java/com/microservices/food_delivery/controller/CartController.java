package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.dto.ApiResponse;
import com.microservices.food_delivery.dto.CartRequest;
import com.microservices.food_delivery.entity.Cart;
import com.microservices.food_delivery.security.SecurityUtil;
import com.microservices.food_delivery.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<ApiResponse<Cart>> addToCart(
            @RequestBody CartRequest cartRequest,
            @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();
        Cart addToCart = cartService.addToCart(
                email,
                cartRequest.getFoodId(),
                cartRequest.getQuantity()
        );

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
    public ResponseEntity<ApiResponse<List<Cart>>> viewCart(
            @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();
        List<Cart> viewCart = cartService.getUserCart(email);
        String role = SecurityUtil.getCurrentUserRole();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Cart Viewed",
                        HttpStatus.CONTINUE,
                        role,
                        viewCart
                )
        );
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<ApiResponse<String>> removeItem(@PathVariable Long cartId) {

        cartService.removeItem(cartId);   // 🔥 actual delete

        String role = SecurityUtil.getCurrentUserRole();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Cart Removed",
                        HttpStatus.OK,
                        role,
                        "Deleted successfully"
                )
        );
    }
}



