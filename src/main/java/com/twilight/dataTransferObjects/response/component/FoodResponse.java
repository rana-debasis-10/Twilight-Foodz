package com.twilight.dataTransferObjects.response.component;

import com.twilight.objects.database.Product;

public record FoodResponse(String id,String name, String image, Double price , boolean available, String description) {
    public FoodResponse(Product product){
        this(product.getId(), product.getName(), product.getImage(), product.getPrice(),true, product.getDescription());
    }
}
