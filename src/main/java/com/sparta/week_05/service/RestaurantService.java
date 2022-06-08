package com.sparta.week_05.service;


import com.sparta.week_05.dto.RestaurantRequestDto;
import com.sparta.week_05.dto.RestaurantResponseDto;
import com.sparta.week_05.model.Restaurant;
import com.sparta.week_05.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final ModelMapper modelMapper;
    private final RestaurantRepository restaurantRepository;
    // 음식점 등록
    @Transactional
    public Restaurant add(RestaurantRequestDto requestDto) {
        int minOrderPrice = requestDto.getMinOrderPrice();
        int deliveryFee = requestDto.getDeliveryFee();
        if (minOrderPrice % 100 != 0 || minOrderPrice < 1000 || minOrderPrice > 100000)
            throw new IllegalArgumentException("허용값 또는 100원 단위가 아닙니다.");
        if (deliveryFee % 500 != 0 || deliveryFee < 0 || deliveryFee > 10000)
            throw new IllegalArgumentException("허용값 또는 500원 단위가 아닙니다.");
        Restaurant restaurant = new Restaurant(requestDto);
        return restaurantRepository.save(restaurant);
    }

    // 음식점 조회
    public List<RestaurantResponseDto> read() {
        // List로 id~foods까지 다받아온다.
        List<Restaurant> all = restaurantRepository.findAll();
//        modelMapper.typeMap(Restaurant.class,RestaurantResponseDto.class).addMappings(mapper -> {
//            mapper.map(Restaurant::getDeliveryFee, RestaurantResponseDto::setDeliveryFee);
//        });
        List<RestaurantResponseDto> list1 = all.stream() // 스트림 생성
                .map(list -> modelMapper.map(list, RestaurantResponseDto.class)) // 중간연산
                .collect(Collectors.toList()); // 최종 연산산
        return list1;
    }



}
