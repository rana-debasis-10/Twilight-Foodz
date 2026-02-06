package com.twilight.dataTransferObjects.response.component;

import com.twilight.components.database.Item;

public record ItemResponse (String id,
                            String name,
                            Double price,
                            String image,
                            Integer quantity,
                            Double subtotal
){
    public ItemResponse(Item item){
        this(item.getId(),item.getName(),item.getPrice(),item.getImage(),item.getQuantity(),item.getSubtotal());
    }
}
