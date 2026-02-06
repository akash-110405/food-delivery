package com.microservices.food_delivery.service;

import com.microservices.food_delivery.entity.*;
import com.microservices.food_delivery.repository.FoodRepository;
import com.microservices.food_delivery.repository.OrderRepository;
import com.microservices.food_delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final FoodRepository foodRepository;

    public Order placeOrder(String email, Double totalAmount,
                            Long foodId, Integer quantity){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not Found"));

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.PLACED);
        order.setPaymentStatus(PaymentStatus.PENDING);
        order.setFood(food);
        order.setQuantity(quantity);

        return orderRepository.save(order);
    }

    public Optional<Order> getOrders(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getItemsByOrder(Long orderId) {
        return Collections.singletonList(orderRepository.getById(orderId));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}