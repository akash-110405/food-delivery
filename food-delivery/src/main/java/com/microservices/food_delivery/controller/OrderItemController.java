package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.entity.OrderItem;
import com.microservices.food_delivery.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping
    public OrderItem addOrderItem(@RequestParam Long orderId,
                                  @RequestParam Long foodId,
                                  @RequestParam Integer quantity){
        return orderItemService.addItem(orderId, foodId, quantity);
    }

    @GetMapping("/order/{orderId}")
    public List<OrderItem> getOrderItems(@PathVariable Long orderId){
        return orderItemService.getItemsByOrder(orderId);
    }
}
