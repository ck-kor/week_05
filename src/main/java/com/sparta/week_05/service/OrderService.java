package com.sparta.week_05.service;

import com.sparta.week_05.dto.OrderRequestDto;
import com.sparta.week_05.model.Food;
import com.sparta.week_05.model.Order;
import com.sparta.week_05.model.OrderFoods;
import com.sparta.week_05.model.Restaurant;
import com.sparta.week_05.repository.FoodRepository;
import com.sparta.week_05.repository.OrderRepository;
import com.sparta.week_05.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public Order order(OrderRequestDto requestDto) {
        // 1. restaurantId로 레스토랑 이름, 배달료, 최소주문가격 가져오기
        Restaurant restaurant = restaurantRepository.findById(requestDto.getRestaurantId()).orElseThrow(
                () -> new NullPointerException("해당 음식점을 찾을 수 없습니다")
        );
        String restaurant_name = restaurant.getName();
        int restaurant_delivery = restaurant.getDeliveryFee();
        int restaurant_minOrderPrice = restaurant.getMinOrderPrice();

        // 2. requestDto에 Food_id 뽑아내기
        List<Long> foodId = new ArrayList<>();
        for ( int i=0; i<requestDto.getFoods().size(); i++) {
            foodId.add(requestDto.getFoods().get(i).getId());
        }
        // 3. id로 food이름, 가격 가져오기
        List<Food> foodList = foodRepository.findAllById(foodId);
        // 4. 반환할 foods 만들기 ( food 이름, 개수, 가격 ) + total 금액 계산해두기
        List<OrderFoods> orderFoods = new ArrayList<>();
        int total_bilge = 0;
        for (int i=0; i<foodList.size(); i++) {
            String order_food_name = foodList.get(i).getName();
            int order_food_quantity = requestDto.getFoods().get(i).getQuantity();
            // 5. 주문수량 허용값 범위 안에 드는지 확인
            if (order_food_quantity < 1 || order_food_quantity > 100)
                throw new IllegalArgumentException("주문 수량이 허용값 1~100을 벗어났습니다.");
            int order_food_price = foodList.get(i).getPrice() * order_food_quantity;
            OrderFoods foods = new OrderFoods(order_food_name, order_food_quantity, order_food_price);
            orderFoods.add(foods);
            total_bilge += order_food_price;
        }
        // 6. total 비교하기 minOrderPrice
        if (restaurant_minOrderPrice > total_bilge)
            throw new IllegalArgumentException("최소 주문금액을 만족하지 않습니다.");
        total_bilge += restaurant_delivery;
        // 7. 반환할 order 생성 ( 음식점이름, 음식정보, 배달료, 최종가격 )
        Order order = new Order(restaurant_name, orderFoods, restaurant_delivery, total_bilge);

        orderRepository.save(order);

        return order;
    }

    public List<Order> read() {
        return orderRepository.findAll();
    }
}
