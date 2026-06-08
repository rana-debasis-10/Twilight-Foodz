package com.twilight.serviceImpls;

import com.twilight.objects.*;
import com.twilight.repositories.MerchantRepository;
import com.twilight.repositories.RestaurantRepository;
import com.twilight.services.MerchantService;
import com.twilight.types.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    MerchantRepository merchantRepository;

    @Override
    @Transactional
    public void createOutlet(String mobNo, Point point) {
        Restaurant restaurant = findRestaurantByMobNo(mobNo);
        Outlet outlet = new Outlet();
        outlet.setLatitude(point.lat());
        outlet.setLongitude(point.lon());
        OutletMember owner = new OutletMember();
        owner.setMobNo(mobNo);
        owner.setOutlet(outlet);
        owner.setRole(Role.merchant);
        outlet.getMembers().add(owner);
        restaurant.getOutlet().add(outlet);
        outlet.setRestaurant(restaurant);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void createMerchant(Merchant merchant, Restaurant restaurant) {
        merchant.setRestaurant(restaurant);
        restaurant.setMerchant(merchant);
        merchantRepository.save(merchant);
    }

    @Override
    public void inviteManager(String userMobNo, String inviteeMobNo, Integer outletId) {

    }
    private Restaurant findRestaurantByMobNo(String mobNo){
        return restaurantRepository.findByMerchantMobNo(mobNo).orElse(null);
    };
}
