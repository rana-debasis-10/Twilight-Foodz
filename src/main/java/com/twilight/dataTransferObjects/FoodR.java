package com.twilight.dataTransferObjects;

public record FoodR(
        String foodId,
        String name,
        String image,
        Double price,
        boolean available
) {}