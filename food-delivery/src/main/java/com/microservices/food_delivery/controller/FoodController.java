package com.microservices.food_delivery.controller;

import com.microservices.food_delivery.entity.Food;
import com.microservices.food_delivery.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Food addFood(@RequestBody Food food){
        return foodService.addFood(food);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Food> getAllFoods(){
        return foodService.getAllFoods();
    }

    @GetMapping("/available")
    public List<Food> getAvailableFoods(){
        return foodService.getAvailableFoods();
    }

    @PostMapping("{id}")
    public Food getFoodById(@PathVariable Long id){
        return foodService.getFoodById(id);
    }
}
