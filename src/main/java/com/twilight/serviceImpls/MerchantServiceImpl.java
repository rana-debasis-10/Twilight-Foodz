package com.twilight.serviceImpls;

import com.twilight.annotations.MobileNumber;
import com.twilight.dataTransferObjects.OutletDetailed;
import com.twilight.dataTransferObjects.Point;
import com.twilight.exceptions.BadRequestException;
import com.twilight.exceptions.NotFoundException;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.objects.*;
import com.twilight.repositories.MerchantRepository;
import com.twilight.repositories.OutletInvitationRepository;
import com.twilight.repositories.OutletRepository;
import com.twilight.repositories.RestaurantRepository;
import com.twilight.services.MerchantService;
import com.twilight.types.OutletStatus;
import jakarta.transaction.Transactional;
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
    public Merchant getMerchant(String mobNo) {
        return merchantRepository.findById(mobNo).orElseThrow(
                ()-> new NotFoundException(
                        "Merchant not found"
                        ,"Could not find you linked account"));
    }

    @Override
    public OutletInvitation inviteManager(String inviterMobNo, String inviteeMobNo, Integer outletId) {
        Outlet outlet = outletRepository.findById(outletId)
                            .orElseThrow(
                                    ()-> new NotFoundException(
                                            "User trying to send invitation for outlet that does not exist",
                                            "Outlet does not exist"
                                    ));
        if(!outlet.getMerchantMobNo().equals(inviterMobNo))
            throw new UnAuthorizedException(
                    "User is trying to send invitation of outlet that is not his",
                    "Outlet does not belong to your restaurant"
            );
        OutletInvitation invitation = new OutletInvitation();
        invitation.setInviterMobileNo(inviterMobNo);
        invitation.setInviteeMobileNo(inviteeMobNo);
        invitation.setOutletId(outletId);
        return invitationRepository.save(invitation);
    }

    @Override
    public OutletInvitation inviteOtherManager(String merchantMobNo, String inviteeMobNo, Integer outletId) throws BadRequestException {
        OutletInvitation invitation = invitationRepository.findByOutletId(outletId)
                .orElseThrow(
                    ()-> new BadRequestException(
                            "User is trying to find a invitation",
                            "Invitation does not exist"));
        invitation.setInviteeMobileNo(inviteeMobNo);
        return invitationRepository.save(invitation);
    }
    @Override
    public List<OutletDetailed> viewAllOutlets(@MobileNumber String merchantMobNo){
        return outletRepository.findAllByMerchantMobNo(merchantMobNo);
    };

    @Override
    public Restaurant findRestaurantByMobNo(String mobNo){
        return restaurantRepository.findByMerchantMobNo(mobNo).orElse(null);
    };


}
