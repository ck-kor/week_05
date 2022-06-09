package com.sparta.week_05.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.week_05.dto.FoodRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_NUMBER")
    private Restaurant restaurant;


    public Food(FoodRequestDto requestDto, Restaurant restaurant) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurant = restaurant;
    }


        public Food(FoodRequestDto requestDto, Long restaurantId) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurantId = restaurantId;
    }
}
