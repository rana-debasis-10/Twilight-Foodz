package com.twilight.serviceImpls;

import com.twilight.dataTransferObjects.Point;
import com.twilight.objects.*;
import com.twilight.repositories.MerchantRepository;
import com.twilight.repositories.OutletInvitationRepository;
import com.twilight.repositories.RestaurantRepository;
import com.twilight.services.MerchantService;
import com.twilight.types.InvitationStatus;
import com.twilight.types.OutletStatus;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public OutletInvitation inviteManager(String inviterMobNo, String inviteeMobNo, String outletId) {
        OutletInvitation invitation = new OutletInvitation();
        invitation.setInviteeMobileNo(inviteeMobNo);
        invitation.setOutletId(outletId);
        invitation.setStatus(InvitationStatus.pending);
        return invitationRepository.save(invitation);
    }

    @Override
    public OutletInvitation inviteOtherManager(String merchantMobNo, String inviteeMobNo, Integer invitationId, String outletId) throws BadRequestException {
        OutletInvitation invitation = invitationRepository.findById(invitationId).orElseThrow(BadRequestException::new);
        if(invitation.getOutletId().equals(outletId)){
            throw new BadRequestException();
        }
        invitationRepository.delete(invitation);
        return inviteManager(merchantMobNo,inviteeMobNo,outletId);
    }

    private Restaurant findRestaurantByMobNo(String mobNo){
        return restaurantRepository.findByMerchantMobNo(mobNo).orElse(null);
    };


}
