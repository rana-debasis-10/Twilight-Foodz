package com.twilight.dataTransferObjects.response.component;

import com.twilight.components.database.Restaurant;
import com.twilight.dataTransferObjects.request.AddressDetails;

public record RestaurantResponse(String name, AddressDetails addressDetails, String image,int rating ) {
    public RestaurantResponse(Restaurant restaurant){
        this(restaurant.getName(), new AddressDetails(restaurant.getAddress()), restaurant.getImage(), restaurant.getRating());
    }
}
