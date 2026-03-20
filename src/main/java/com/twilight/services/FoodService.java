package com.twilight.services;

import com.twilight.objects.Food;
import org.springframework.stereotype.Service;


public interface FoodService {
    void createFood(Food food);
    void updateFood(Food food);
    void deleteFood(Food food);
}
