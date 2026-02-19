package com.twilight.dataTransferObjects.response.component;

import com.twilight.objects.database.Restaurant;
import com.twilight.dataTransferObjects.request.AddressDetails;

public record RestaurantResponse(String name, AddressDetails addressDetails, String image,int rating ) {
    public RestaurantResponse(Restaurant restaurant){
        this(restaurant.getName(), new AddressDetails(restaurant.getLocation()), restaurant.getImage(), restaurant.getRating());
    }
}
