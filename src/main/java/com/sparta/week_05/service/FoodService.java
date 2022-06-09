package com.sparta.week_05.service;

import com.sparta.week_05.dto.FoodRequestDto;
import com.sparta.week_05.dto.FoodResponseDto;
import com.sparta.week_05.model.Food;
import com.sparta.week_05.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void add(Long restaurantId, List<FoodRequestDto> requestDto) {
        // 1. requestDto 안에 name 이 겹치는게 없는지
        List<String> requestDtoName = new ArrayList<>();
        List<Integer> requestDtoPrice = new ArrayList<>();
        for (FoodRequestDto foodRequestDto : requestDto) {
            requestDtoName.add(foodRequestDto.getName());
            requestDtoPrice.add(foodRequestDto.getPrice());
        }
        if(requestDto.size() != requestDtoName.stream().distinct().count())
            throw new IllegalArgumentException("입력된 음식명이 중복되었습니다");
        // 2. 같은 restId 안에서는 음식이름 중복 불가
        for (String requestDtoFoodName : requestDtoName) {
            Food foodss = foodRepository.findByNameContainsAndRestaurantId(requestDtoFoodName, restaurantId);
            if (foodss != null) {
                if (foodss.getName().equals(requestDtoFoodName)) {
                    throw new IllegalArgumentException("해당 음식점에 존재하는 메뉴입니다.");
                }
            }
        }
        // 3. 가격 100~ 1000000 + % 100 == 0 이여야 된다.
        for (Integer integer : requestDtoPrice) {
            if (integer % 100 != 0 || integer > 1000000 || integer < 100)
                throw new IllegalArgumentException("허용값이 아니거나 100원단위가 아닙니다.");
        }
        List<Food> foodsList = new ArrayList<>();
        for (FoodRequestDto foodRequestDto : requestDto) {
            Food foods = new Food(foodRequestDto, restaurantId);
            foodsList.add(foods);
        }
        // 4. 검사 끝나면 requestDto로 메뉴등록
        foodRepository.saveAll(foodsList);

//        return "메뉴 등록 성공";
    }

    public List<FoodResponseDto> read(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId).stream()
                .map(list -> modelMapper.map(list, FoodResponseDto.class))
                .collect(Collectors.toList());
    }
}
