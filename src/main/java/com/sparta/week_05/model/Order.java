package com.sparta.week_05.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity ( name = "orders")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    private String restaurantName;
    // 위 아래 둘다 restaurant에서 가져올 수 있는것들
    @JoinColumn(name = "ORDER_ID")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderFoods> foods = new ArrayList<>();

    @Column
    private int deliveryFee;

    @Column(nullable = false)
    private int totalPrice;

    public Order(String restaurant_name, List<OrderFoods> orderFoods, int restaurant_delivery, int total_bilge) {
        this.restaurantName = restaurant_name;
        this.foods = orderFoods;
        this.deliveryFee = restaurant_delivery;
        this.totalPrice = total_bilge;
    }
}
