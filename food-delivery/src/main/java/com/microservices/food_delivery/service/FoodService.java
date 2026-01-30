package com.microservices.food_delivery.service;

import com.microservices.food_delivery.entity.Food;
import com.microservices.food_delivery.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public Food addFood(Food food){
        return foodRepository.save(food);
    }
    public List<Food> getAllFoods(){
        return foodRepository.findAll();
    }
    public List<Food> getAvailableFoods(){
        return foodRepository.findByAvailableTrue();
    }
    public Food getFoodById(Long id){
        return foodRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Food not found"));
    }
}
