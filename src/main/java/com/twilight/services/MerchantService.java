package com.twilight.services;

import com.twilight.components.database.Food;
import com.twilight.components.database.Merchant;
import com.twilight.components.database.Restaurant;
import com.twilight.components.security.UserDetailsImpl;
import com.twilight.dataTransferObjects.request.FoodRequest;
import com.twilight.dataTransferObjects.response.component.MerchantResponse;
import com.twilight.exceptions.LimitExceeded;
import com.twilight.exceptions.UserAlreadyExists;
import com.twilight.repositories.CustomerRepo;
import com.twilight.repositories.MerchantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class MerchantService {
    RestaurantService restaurantService;

    FoodService foodService;

    MerchantRepo merchantRepo;

    /// Constructor
    @Autowired
    public MerchantService(RestaurantService restaurantService, FoodService foodService, MerchantRepo merchantRepo) {
        this.restaurantService = restaurantService;
        this.foodService = foodService;
        this.merchantRepo = merchantRepo;
    }

    @Transactional
    public List<MerchantResponse>getAll(int page){
        return merchantRepo.findAllMerchants(PageRequest.of(page,10));
    }

    public void save(Merchant merchant) throws Exception {
        try
        {
            merchantRepo.save(merchant);
        }
        catch(Exception e){
            throw new UserAlreadyExists(e.getMessage());
        }
    }

    public Merchant get(String merchantId) throws Exception {
        return merchantRepo.findById(merchantId).orElse(null);
    }

    @Transactional
    public void createFood(List<FoodRequest> request) {
        UserDetailsImpl userDetails = (UserDetailsImpl) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
        assert userDetails != null;
        String customerId = userDetails.getId();
        Restaurant restaurant =restaurantService.getById(customerId);
        if(request.size()>15){
            throw new LimitExceeded(request.size());
        }

        List<Food> foods = request.stream().map(Food::new).toList();
        restaurant.getFoods().addAll(foods);
        foods.forEach(food -> {food.setRestaurant(restaurant);});
        restaurantService.saveRestaurant(restaurant);
    }

    @Transactional
    public MerchantResponse getById(String merchantId) throws Exception {
        return merchantRepo.findMerchantById(merchantId);
    }

    @Transactional
    public List<MerchantResponse> getByName(String merchantName,int page) throws Exception {
        return merchantRepo.findAllMerchantsByName(merchantName,PageRequest.of(page,20));
    }
}
