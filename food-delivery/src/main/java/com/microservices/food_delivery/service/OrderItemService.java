package com.microservices.food_delivery.service;

import com.microservices.food_delivery.entity.Food;
import com.microservices.food_delivery.entity.Order;
import com.microservices.food_delivery.entity.OrderItem;
import com.microservices.food_delivery.repository.FoodRepository;
import com.microservices.food_delivery.repository.OrderItemRepository;
import com.microservices.food_delivery.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;

    public OrderItem addItem(Long orderId, Long foodId, Integer quantity) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Food not Found"));

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setFood(food);
        item.setQuantity(quantity);
        item.setPrice(food.getPrice());

        return orderItemRepository.save(item);
    }

    public List<OrderItem> getItemsByOrder(Long orderId){
        return orderItemRepository.findByOrderId(orderId);
    }

}
