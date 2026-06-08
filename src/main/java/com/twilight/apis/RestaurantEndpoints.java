package com.twilight.apis;

import com.twilight.dataTransferObjects.AddressR;
import com.twilight.dataTransferObjects.MerchantR;
import com.twilight.dataTransferObjects.RestaurantR;

import com.twilight.exceptions.UnauthorizedException;
import com.twilight.mappers.MerchantMapper;
import com.twilight.mappers.RestaurantMapper;
import com.twilight.objects.Merchant;
import com.twilight.objects.Point;
import com.twilight.objects.Restaurant;
import com.twilight.services.*;
import com.twilight.types.Role;
import com.twilight.utils.UserContext;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/restaurant")
public class RestaurantEndpoints {
    @Autowired
    private MerchantService merchantService;

    @Autowired
    JwtService jwtService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    UserContext userContext;

    @Autowired
    GeoCodingService geoCoding;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public String register(@RequestPart("merchant") MerchantR merchantR,
                         @RequestPart("restaurant") RestaurantR restaurantR,
                         @RequestPart("file") MultipartFile image) throws IOException {

            Restaurant restaurant = restaurantMapper.toRestaurant(restaurantR);
            Merchant merchant = merchantMapper.toMerchant(merchantR);
            String mobNo = userContext.getMobile_Number();
            merchant.setMobNo(mobNo);
            restaurant.setImage(storageService.upload(image, "restaurant"));
            merchantService.createMerchant(merchant, restaurant);
            return jwtService.generateToken(mobNo, Role.merchant);
    }
    @PostMapping("/create/outlet")
    @Transactional
    public void create(@RequestBody AddressR address) throws UnauthorizedException {
        String mobNo = userContext.getMobile_Number();
        Point point = geoCoding.getLocation(address);
        merchantService.createOutlet(mobNo,point);
    }





}
