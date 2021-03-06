package com.sparta.week_05.model;


import com.sparta.week_05.dto.RestaurantRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 음식점 이름
    @Column(nullable = false, unique = true)
    private String name;
    // 최소주문비용
    @Column(nullable = false)
    private int minOrderPrice;
    // 기본 배달료
    @Column(nullable = false)
    private int deliveryFee;


    public Restaurant(RestaurantRequestDto requestDto) {
        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }
}
