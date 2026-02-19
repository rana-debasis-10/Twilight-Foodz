package com.twilight.services;

import com.twilight.objects.database.*;
import com.twilight.objects.security.UserDetailsImpl;
import com.twilight.dataTransferObjects.authentication.BasicUserDetails;
import com.twilight.dataTransferObjects.request.KycRequest;
import com.twilight.dataTransferObjects.request.RestaurantCreateRequest;
import com.twilight.dataTransferObjects.request.VehicleRequest;
import com.twilight.exceptions.UserAlreadyExists;
import com.twilight.repositories.DriverRepo;
import com.twilight.repositories.MerchantRepo;
import com.twilight.repositories.OnBoardingRepo;
import com.twilight.types.OnBoarder;
import com.twilight.types.OnBoardingState;
import com.twilight.types.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class OnBoardingService {

    private final OnBoardingRepo onBoardingRepo;

    private final CustomerService customerService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final MerchantService merchantService;
    private final DriverRepo driverRepo;

    @Autowired
    public OnBoardingService(OnBoardingRepo onBoardingRepo, CustomerService customerService, JwtService jwtService, AuthenticationManager authenticationManager, MerchantRepo merchantRepo, MerchantService merchantService, DriverRepo driverRepo) {
        this.onBoardingRepo = onBoardingRepo;
        this.customerService = customerService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.merchantService = merchantService;
        this.driverRepo = driverRepo;
    }


    @Transactional
    public String startOnBoarding(BasicUserDetails basicUserDetails, OnBoarder type) throws Exception {
        try{
            Customer customer = customerService.save(basicUserDetails);
            onBoardingRepo.save(new OnBoarding(customer.getId(), type, OnBoardingState.BASIC));
            return jwtService.generateToken(customer.getId(),customer.getRole(),customer.getMobNo());

        }
        catch (UserAlreadyExists exists){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(basicUserDetails.email(), basicUserDetails.password()));
            if (authentication.isAuthenticated()) {
                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                assert userDetails != null;
                onBoardingRepo.save(new OnBoarding(userDetails.getId(), type, OnBoardingState.BASIC));
                return jwtService.generateToken(userDetails.getId(),userDetails.getRole(),userDetails.getMobNo());
            }
            else
                throw new Exception("Incorrect Credentials");
        }

    }
    @Transactional
    public void doKyc(KycRequest kycRequest) throws Exception {
        UserDetailsImpl userDetails = (UserDetailsImpl) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
        assert userDetails != null;
        String customerId = userDetails.getId();
        Optional<OnBoarding> onBoardingDetails =onBoardingRepo.findById(customerId);
        if(onBoardingDetails.isPresent()){
            switch(onBoardingDetails.get().getType()){
                case DRIVER:
                    kycForMerchant(customerId,onBoardingDetails.get(),kycRequest);
                    break;
                case RESTAURANT:
                    kycForDriver(customerId,onBoardingDetails.get(),kycRequest);
            };
        }
    }
    private void kycForMerchant(String customerId, OnBoarding onBoardingDetail, KycRequest kycRequest) throws Exception {
        switch (onBoardingDetail.getOnBoardingState()){
            case BASIC:
                Customer customer = customerService.getById(customerId);
                Merchant merchant = new Merchant(kycRequest);
                customer.setMerchant(merchant);
                merchant.setCustomer(customer);
                onBoardingDetail.setOnBoardingState(OnBoardingState.KYC);
                customerService.update(customer);
                break;
            case KYC:
                return;
            case COMPLETE:
                break;
        }
    }

    private void kycForDriver(String customerId, OnBoarding onBoardingDetails, KycRequest kycRequest) throws Exception {
        switch (onBoardingDetails.getOnBoardingState()){
            case KYC:
                throw new Exception("Already Completed");
            case BASIC:
                Customer customer = customerService.getById(customerId);
                Driver driver = new Driver(kycRequest);
                customer.setDriver(driver);
                driver.setCustomer(customer);
                customerService.update(customer);
                onBoardingDetails.setOnBoardingState(OnBoardingState.KYC);
                onBoardingRepo.save(onBoardingDetails);
            case COMPLETE:
                throw new Exception("Already Completed");

        }
    }

    @Transactional
    public String completeOnboardingForRestaurant(RestaurantCreateRequest restaurantCreateRequest) throws Exception {
        UserDetailsImpl userDetails = (UserDetailsImpl) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
        assert userDetails != null;
        String customerId = userDetails.getId();
        Optional<OnBoarding> onBoardingDetails =onBoardingRepo.findById(customerId);
        if(onBoardingDetails.isPresent()){
            if (Objects.requireNonNull(onBoardingDetails.get().getType()) == OnBoarder.RESTAURANT && onBoardingDetails.get().getOnBoardingState()==OnBoardingState.KYC ) {
                Merchant merchant = merchantService.get(customerId);
                Restaurant restaurant = new Restaurant(restaurantCreateRequest);
                merchant.setRestaurant(restaurant);
                restaurant.setMerchant(merchant);
                merchantService.save(merchant);
                onBoardingDetails.get().setOnBoardingState(OnBoardingState.COMPLETE);
                onBoardingRepo.save(onBoardingDetails.get());
                return jwtService.generateToken(customerId, Role.MERCHANT, userDetails.getMobNo());
            }
            throw new RuntimeException("Wrong Credentials");
        }
        else {
            throw new Exception("Start On-Boarding First");
        }
    }

    @Transactional
    public String completeOnBoardingForDriver(VehicleRequest request) throws Exception {
        UserDetailsImpl userDetails = (UserDetailsImpl) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
        assert userDetails != null;
        String customerId = userDetails.getId();
        Optional<OnBoarding> onBoardingDetails = onBoardingRepo.findById(customerId);
        if(onBoardingDetails.isPresent()){
            if (Objects.requireNonNull(onBoardingDetails.get().getType()) == OnBoarder.DRIVER && onBoardingDetails.get().getOnBoardingState()==OnBoardingState.KYC ) {
                Optional<Driver> driver = driverRepo.findById(customerId);
                if (driver.isPresent()) {
                    Vehicle vehicle = new Vehicle(request);
                    vehicle.setDriver(driver.get());
                    driver.get().setVehicle(vehicle);
                    onBoardingDetails.get().setOnBoardingState(OnBoardingState.COMPLETE);
                    onBoardingRepo.save(onBoardingDetails.get());
                    driverRepo.save(driver.get());
                    return jwtService.generateToken(customerId, Role.DRIVER, userDetails.getMobNo());
                }

            }
            throw new RuntimeException("Wrong Credentials");
        }
        else {
            throw new Exception("Start On-Boarding First");
        }
    }
}
