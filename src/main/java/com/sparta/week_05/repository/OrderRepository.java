package com.sparta.week_05.repository;

import com.sparta.week_05.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
