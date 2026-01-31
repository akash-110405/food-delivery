package com.microservices.food_delivery.service;

import com.microservices.food_delivery.entity.Order;
import com.microservices.food_delivery.entity.OrderStatus;
import com.microservices.food_delivery.entity.PaymentStatus;
import com.microservices.food_delivery.entity.User;
import com.microservices.food_delivery.repository.OrderRepository;
import com.microservices.food_delivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public Order placeOrder(String email, Double totalAmount){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.PLACED);
        order.setPaymentStatus(PaymentStatus.PENDING);

        return orderRepository.save(order);

    }


}
