package com.twilight.dataTransferObjects.response.component;

import com.twilight.components.database.Food;

public record FoodResponse(String id,String name, String image, Double price , boolean available, String description) {
    public FoodResponse(Food food){
        this(food.getId(), food.getName(),food.getImage(),food.getPrice(),true, food.getDescription());
    }
}
