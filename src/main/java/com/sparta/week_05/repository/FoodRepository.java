package com.sparta.week_05.repository;

import com.sparta.week_05.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurantId(Long RestaurantId);
//    Food findByRestaurantIdAndNameContains(Long RestaurantId, String name);

    Food findByNameContainsAndRestaurantId(String name, Long RestaurantId);
}
