package com.microservices.food_delivery.dto;

import lombok.Data;

@Data
public class CartRequest {

    private Long foodId;
    private Integer quantity;
}
