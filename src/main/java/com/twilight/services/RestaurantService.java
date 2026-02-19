package com.twilight.services;

import com.twilight.objects.database.Restaurant;
import com.twilight.dataTransferObjects.response.component.RestaurantResponse;
import com.twilight.repositories.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RestaurantService {

    private final RestaurantRepo restaurantRepo;

    @Autowired
    /// Constructor ///
    public RestaurantService(RestaurantRepo restaurantRepo) {
        this.restaurantRepo = restaurantRepo;
    }

    public List<RestaurantResponse> getAllRestaurant(int pageNum) throws Exception{
        return restaurantRepo.findAll(PageRequest.of(pageNum,10))
                .getContent()
                .stream()
                .map(RestaurantResponse::new)
                .toList();
    }

    public List<Restaurant> getRestaurantByName(String name, int pageNum) throws Exception{
        return restaurantRepo.findByNameContainingIgnoreCase(name,PageRequest.of(pageNum,15)).getContent();
    }

    public Restaurant getById(String id){
        return restaurantRepo.findById(id).orElse(null);
    }

    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepo.save(restaurant);
    }
}
