package com.twilight.serviceImpls;

import com.twilight.objects.Food;
import com.twilight.repositories.FoodRepository;
import com.twilight.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private FoodRepository foodRepository;
    @Override
    public void createFood(Food food) {
        foodRepository.save(food);
    }

    @Override
    public void updateFood(Food food) {
        foodRepository.save(food);
    }

    @Override
    public void deleteFood(Food food) {
        foodRepository.delete(food);
    }
}
