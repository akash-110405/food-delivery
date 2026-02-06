package com.microservices.food_delivery.service;

import com.microservices.food_delivery.entity.*;
import com.microservices.food_delivery.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;

    public Cart addToCart(String email, Long foodId, Integer quantity) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        // If same food already in cart → update quantity
        Cart cart = cartRepository
                .findByUserIdAndFoodId(user.getId(), foodId)
                .orElse(new Cart());

        cart.setUser(user);
        cart.setFood(food);
        cart.setQuantity(
                cart.getId() == null ? quantity : cart.getQuantity() + quantity
        );

        return cartRepository.save(cart);
    }

    public List<Cart> getUserCart(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepository.findByUserId(user.getId());
    }

    public void removeCartItem(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public void clearCart(Long userId) {
        cartRepository.findByUserId(userId)
                .forEach(cartRepository::delete);
    }


}

