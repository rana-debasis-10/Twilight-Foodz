package com.twilight.serviceImpls;

import com.twilight.objects.*;
import com.twilight.repositories.MerchantRepository;
import com.twilight.repositories.OutletInvitationRepository;
import com.twilight.repositories.RestaurantRepository;
import com.twilight.services.MerchantService;
import com.twilight.types.InvitationStatus;
import com.twilight.types.OutletStatus;
import com.twilight.types.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    OutletInvitationRepository invitationRepository;

    @Override
    @Transactional
    public void createOutlet(String mobNo, Point point) {
        Restaurant restaurant = findRestaurantByMobNo(mobNo);
        Outlet outlet = new Outlet();
        outlet.setLatitude(point.lat());
        outlet.setLongitude(point.lon());
        outlet.setOutletStatus(OutletStatus.closed);
        outlet.setMerchant(mobNo);
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
    public void inviteManager(String inviterMobNo, String inviteeMobNo, String outletId) {
        OutletInvitation invitation = new OutletInvitation();
        invitation.setInviteeMobileNo(inviteeMobNo);
        invitation.setOutletId(outletId);
        invitation.setStatus(InvitationStatus.pending);
        invitationRepository.save(invitation);
    }
    private Restaurant findRestaurantByMobNo(String mobNo){
        return restaurantRepository.findByMerchantMobNo(mobNo).orElse(null);
    };


}
