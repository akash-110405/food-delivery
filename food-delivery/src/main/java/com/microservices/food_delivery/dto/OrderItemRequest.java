package com.microservices.food_delivery.dto;

import lombok.Data;

@Data
public class OrderItemRequest {

    private Long orderId;
    private Long foodId;
    private Integer quantity;
}
