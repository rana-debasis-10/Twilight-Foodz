package com.twilight.services;

import com.twilight.objects.Merchant;
import com.twilight.objects.OutletInvitation;
import com.twilight.dataTransferObjects.Point;
import com.twilight.objects.Restaurant;
import org.apache.coyote.BadRequestException;

public interface MerchantService {
    void createOutlet(String mobNo, Point point);
    void createMerchant(Merchant merchant, Restaurant restaurant);
    OutletInvitation inviteManager(String merchantMobNo, String inviteeMobNo, String outletId);
    OutletInvitation inviteOtherManager(String merchantMobNo, String inviteeMobNo, Integer invitationId, String outletId) throws BadRequestException;
}
