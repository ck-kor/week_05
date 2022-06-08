package com.sparta.week_05.controller;


import com.sparta.week_05.dto.OrderRequestDto;
import com.sparta.week_05.model.Order;
import com.sparta.week_05.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order/request")
    public Order order(@RequestBody OrderRequestDto requestDto) {
        return orderService.order(requestDto);
    }

    @GetMapping("/orders")
    public List<Order> orderRead() {
        return orderService.read();
    }
}
