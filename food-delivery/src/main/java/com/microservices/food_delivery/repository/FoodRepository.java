package com.microservices.food_delivery.repository;

import com.microservices.food_delivery.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food,Long> {

    List<Food> findByAvailableTrue();
    List<Food> findByCategoryId(Long categoryId);
}
