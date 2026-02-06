package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.dto.ApiResponse;
import com.microservices.food_delivery.entity.Food;
import com.microservices.food_delivery.security.SecurityUtil;
import com.microservices.food_delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    public ResponseEntity<ApiResponse<Food>> addFood(@RequestBody Food food){
        Food addFood =  foodService.addFood(food);
        String role = SecurityUtil.getCurrentUserRole();

        return ResponseEntity.ok(
                new ApiResponse<>("Food added successfully",
                        HttpStatus.CREATED,
                        role,
                        addFood));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Food>>> getAllFoods(){
        List<Food> getAllFoods = foodService.getAllFoods();
        String role = SecurityUtil.getCurrentUserRole();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Foods fetched successfully",
                        HttpStatus.ACCEPTED,
                        role,
                        getAllFoods)
        );
    }

    @GetMapping("/available")
    public ResponseEntity<ApiResponse<List<Food>>> getAvailableFoods(){
        List<Food> getAvailableFoods = foodService.getAvailableFoods();
        String role = SecurityUtil.getCurrentUserRole();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Available Foods",
                        HttpStatus.MULTIPLE_CHOICES,
                        role,
                        getAvailableFoods
                )
        );
    }

    @PostMapping("{id}")
    public ResponseEntity<ApiResponse<Food>> getFoodById(@PathVariable Long id){
        Food getFoodById = foodService.getFoodById(id);
        String role = SecurityUtil.getCurrentUserRole();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Foods fetched By Id",
                        HttpStatus.CONTINUE,
                        role,
                        getFoodById
                )
        );
    }
}
