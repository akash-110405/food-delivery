package com.microservices.food_delivery.dto;

import com.microservices.food_delivery.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<D> {

    private String message;
    private HttpStatus statusCode;
    private String role;
    private D data;
}
