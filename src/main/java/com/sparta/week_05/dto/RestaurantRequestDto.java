package com.sparta.week_05.dto;


import com.sparta.week_05.model.Restaurant;
import lombok.Getter;

import java.util.List;

@Getter
public class RestaurantRequestDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
