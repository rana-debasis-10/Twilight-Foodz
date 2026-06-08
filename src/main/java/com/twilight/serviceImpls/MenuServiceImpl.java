package com.twilight.serviceImpls;

import com.twilight.objects.*;
import com.twilight.repositories.RestaurantRepository;
import com.twilight.services.MenuService;
import com.twilight.types.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    RestaurantRepository restaurantRepository;
    public Restaurant findRestaurantByMobNo(String mobNo){
        return restaurantRepository.findByMerchantMobNo(mobNo).orElse(null);
    }

    @Override
    public void addProduct(String restaurantId ,Product product) {
        
    }

    @Override
    public void overrideFoodPrice(String outletId, String foodId) {

    }
}
