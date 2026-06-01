package com.twilight.services;

import com.twilight.objects.Merchant;
import com.twilight.objects.Restaurant;

public interface MerchantService {
    void create(Merchant merchant, Restaurant restaurant);
}
