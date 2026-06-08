package com.twilight.services;

import com.twilight.objects.Merchant;
import com.twilight.objects.Point;
import com.twilight.objects.Restaurant;

public interface MerchantService {
    void createOutlet(String mobNo, Point point);
    void createMerchant(Merchant merchant, Restaurant restaurant);
    void inviteManager(String userMobNo, String inviteeMobNo, Integer outletId);
}
