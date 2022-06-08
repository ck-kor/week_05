package com.sparta.week_05.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity()
@NoArgsConstructor
public class OrderFoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @Column
    private String name;
    @Column
    private int quantity;
    @Column
    private int price;

    public OrderFoods(String name, int quantity, int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
