package com.microservices.food_delivery.repository;

import com.microservices.food_delivery.entity.Order;
import com.microservices.food_delivery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<User> findByUserId(Long userId);

    Optional<Order> findById(Long aLong);
}
