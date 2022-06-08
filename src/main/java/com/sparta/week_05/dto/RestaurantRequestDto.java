package com.sparta.week_05.dto;


import lombok.Getter;

@Getter
public class RestaurantRequestDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
