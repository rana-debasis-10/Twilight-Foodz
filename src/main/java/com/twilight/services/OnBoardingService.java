package com.twilight.services;

import com.twilight.objects.Driver;
import com.twilight.objects.Merchant;
import com.twilight.objects.Restaurant;

public interface OnBoardingService {
    public void onboardingForMerchant(Merchant merchant, Restaurant restaurant);
    public void onboardingForDriver(Driver driver);
}
