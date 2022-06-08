package com.sparta.week_05.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.week_05.dto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 음식 이름
    @Column(nullable = false)
    private String name;
    // 음식 가격
    @Column(nullable = false)
    private int price;

    @Column
    @JsonIgnore
    private Long restaurantId;


        public Food(FoodRequestDto requestDto, Long restaurantId) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurantId = restaurantId;
    }
}
