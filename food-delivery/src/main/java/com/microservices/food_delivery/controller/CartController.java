package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.entity.Cart;
import com.microservices.food_delivery.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public Cart addToCart(@RequestParam Long foodId,
                          @RequestParam Integer quantity,
                          Authentication authentication) {

        String email = authentication.getName();
        return cartService.addToCart(email, foodId, quantity);
    }

    @GetMapping
    public List<Cart> viewCart(Authentication authentication) {
        return cartService.getUserCart(authentication.getName());
    }

    @DeleteMapping("/{cartId}")
    public String removeItem(@PathVariable Long cartId) {
        return "Removed";
    }
}



