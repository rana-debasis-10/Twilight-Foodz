package com.twilight.serviceImpls;

import com.twilight.objects.Driver;
import com.twilight.objects.Merchant;
import com.twilight.objects.Restaurant;
import com.twilight.services.OnBoardingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OnBoardingServiceImpl implements OnBoardingService {
    @Override
    public void onboardingForMerchant(Merchant merchant, Restaurant restaurant) {

    }

    @Override
    public void onboardingForDriver(Driver driver) {

    }
}
