package com.microservices.food_delivery.service;

import com.microservices.food_delivery.entity.Food;
import com.microservices.food_delivery.entity.User;
import com.microservices.food_delivery.repository.FoodRepository;
import com.microservices.food_delivery.repository.UserRepository;
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
    public Food updateFood(Food food){
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
    public void removeFood(Long id) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));

         food.setAvailable(false);
         foodRepository.deleteById(id);
    }
}
