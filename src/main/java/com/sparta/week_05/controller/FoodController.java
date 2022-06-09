package com.sparta.week_05.controller;


import com.sparta.week_05.dto.FoodRequestDto;
import com.sparta.week_05.dto.FoodResponseDto;
import com.sparta.week_05.model.Food;
import com.sparta.week_05.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void addFood(@PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> requestDto){
        foodService.add(restaurantId, requestDto);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodResponseDto> readFoods(@PathVariable Long restaurantId) {

        return foodService.read(restaurantId);
    }

}
