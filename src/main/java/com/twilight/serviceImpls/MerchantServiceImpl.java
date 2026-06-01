package com.twilight.serviceImpls;

import com.twilight.objects.Merchant;
import com.twilight.objects.Restaurant;
import com.twilight.repositories.MerchantRepository;
import com.twilight.repositories.RestaurantRepository;
import com.twilight.services.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    MerchantRepository merchantRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Override
    public void create(Merchant merchant , Restaurant restaurant) {
        merchant.setRestaurant(restaurant);
        restaurant.setMerchant(merchant);
        merchantRepository.save(merchant);
    }
}
