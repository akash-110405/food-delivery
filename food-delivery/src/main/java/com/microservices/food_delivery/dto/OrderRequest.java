package com.microservices.food_delivery.dto;

import com.microservices.food_delivery.entity.Food;
import lombok.Data;

@Data
public class OrderRequest {
    private Double totalAmount;
    private Long foodId;
    private Integer quantity;
}
