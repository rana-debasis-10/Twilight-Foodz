package com.twilight.serviceImpls;

import com.twilight.annotations.MobileNumber;
import com.twilight.dataTransferObjects.OutletDetailed;
import com.twilight.dataTransferObjects.Point;
import com.twilight.objects.*;
import com.twilight.repositories.MerchantRepository;
import com.twilight.repositories.OutletInvitationRepository;
import com.twilight.repositories.OutletRepository;
import com.twilight.repositories.RestaurantRepository;
import com.twilight.services.MerchantService;
import com.twilight.types.OutletStatus;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    MerchantRepository merchantRepository;

    @Autowired
    OutletInvitationRepository invitationRepository;
    @Autowired
    OutletRepository outletRepository;

    @Override
    @Transactional
    public void createOutlet(String mobNo, Point point) {
        Restaurant restaurant = findRestaurantByMobNo(mobNo);
        Outlet outlet = new Outlet();
        outlet.setLatitude(point.latitude());
        outlet.setLongitude(point.longitude());
        outlet.setOutletStatus(OutletStatus.closed);
        outlet.setMerchantMobNo(mobNo);
        outlet.setRestaurant(restaurant);
        outletRepository.save(outlet);
    }

    @Override
    public void createMerchant(Merchant merchant, Restaurant restaurant) {
        merchant.setRestaurant(restaurant);
        restaurant.setMerchant(merchant);
        merchantRepository.save(merchant);
    }

    @Override
    public OutletInvitation inviteManager(String inviterMobNo, String inviteeMobNo, Integer outletId) {
        OutletInvitation invitation = new OutletInvitation();
        invitation.setInviteeMobileNo(inviteeMobNo);
        invitation.setOutletId(outletId);
        return invitationRepository.save(invitation);
    }

    @Override
    public OutletInvitation inviteOtherManager(String merchantMobNo, String inviteeMobNo, Integer outletId,Integer invitationId) throws BadRequestException {
        OutletInvitation invitation = invitationRepository.findById(invitationId).orElseThrow(BadRequestException::new);
        if(invitation.getOutletId().equals(outletId)){
            throw new BadRequestException();
        }
        invitationRepository.delete(invitation);
        return inviteManager(merchantMobNo,inviteeMobNo,outletId);
    }
    @Override
    public List<OutletDetailed> viewAllOutlets(@MobileNumber String merchantMobNo){
        return outletRepository.findAllByMerchantMobNo(merchantMobNo);
    };

    private Restaurant findRestaurantByMobNo(String mobNo){
        return restaurantRepository.findByMerchantMobNo(mobNo).orElse(null);
    };


}
