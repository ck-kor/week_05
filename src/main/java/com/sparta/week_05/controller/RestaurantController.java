package com.sparta.week_05.controller;


import com.sparta.week_05.dto.RestaurantRequestDto;
import com.sparta.week_05.model.Restaurant;
import com.sparta.week_05.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    // 음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant addRestaurant(@RequestBody RestaurantRequestDto requestDto) {
        return restaurantService.add(requestDto);
    }

    // 음식점 조회
    @GetMapping("/restaurants")
    public List<Restaurant> readRestaurant() {
        return restaurantService.read();
    }

}
