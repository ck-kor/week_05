package com.sparta.week_05.dto;


import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private Long restaurantId;
    List<FoodsDto> foods;
}
