package com.twilight.apis;

import com.twilight.dataTransferObjects.MerchantR;
import com.twilight.dataTransferObjects.RestaurantR;

import com.twilight.mappers.MerchantMapper;
import com.twilight.mappers.RestaurantMapper;
import com.twilight.objects.Merchant;
import com.twilight.objects.Restaurant;
import com.twilight.services.MerchantService;
import com.twilight.services.MessageService;
import com.twilight.services.StorageService;
import com.twilight.utils.UserContext;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/restaurant")
public class RestaurantEndpoints {
    @Autowired
    private MessageService messageService;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    UserContext userContext;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public void register(@RequestPart("merchant") MerchantR merchantR,
                         @RequestPart("restaurant") RestaurantR restaurantR,
                         @RequestPart("file") MultipartFile image) throws IOException {

            Restaurant restaurant = restaurantMapper.toRestaurant(restaurantR);
            Merchant merchant = merchantMapper.toMerchant(merchantR);
            merchant.setMobNo(userContext.getMobile_Number());
            restaurant.setImage(storageService.upload(image, "restaurant"));
            merchantService.create(merchant, restaurant);
    }
    @PostMapping("/create")
    public void create(@RequestBody OutletR outlet)




}
