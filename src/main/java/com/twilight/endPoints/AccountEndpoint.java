package com.twilight.endPoints;

import com.twilight.dataTransferObjects.CustomerR;
import com.twilight.dataTransferObjects.MerchantR;
import com.twilight.dataTransferObjects.RestaurantR;
import com.twilight.mappers.MerchantMapper;
import com.twilight.mappers.RestaurantMapper;
import com.twilight.objects.Customer;
import com.twilight.objects.Merchant;
import com.twilight.objects.OutletInvitation;
import com.twilight.objects.Restaurant;
import com.twilight.services.*;
import com.twilight.types.Role;
import com.twilight.utils.UserContext;
import com.twilight.validators.ImageValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountEndpoint {
    @Autowired
    CustomerService customerService;

    @Autowired
    private UserContext user;

    @Autowired
    JwtService jwtService;

    @Autowired
    ManagerService managerService;

    @Autowired
    RestaurantMapper restaurantMapper;
    @Autowired
    MerchantMapper merchantMapper;

    @Autowired
    ImageValidator imageValidator;

    @Autowired
    StorageService storageService;

    @Autowired
    MerchantService merchantService;




    @GetMapping("/customer/register")
    @Validated
    @Transactional
    public String createCustomer(@RequestParam(name = "n",required = true)String name){
        String mobNo = user.getMobNo();
        customerService.create(mobNo,name);
        return jwtService.generateToken(mobNo, Role.customer,name);
    }

    @GetMapping("/customer/login")
    @Validated
    @Transactional
    String loadCustomer() {
        String mobNo = user.getMobNo();
        Customer customer =customerService.load(mobNo);
        return jwtService.generateToken(mobNo,Role.customer,customer.getName());
    }

    @Validated
    @PostMapping(value = "/merchant/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Transactional
    public String createMerchantAndRestaurant(
            @RequestPart("merchant") MerchantR merchantR,
            @RequestPart("restaurant") RestaurantR restaurantR,
            @RequestPart("image") MultipartFile image
    ) throws IOException {

        imageValidator.validateImage(image);

        Restaurant restaurant = restaurantMapper.toRestaurant(restaurantR);
        Merchant merchant = merchantMapper.toMerchant(merchantR);

        String mobNo = user.getMobNo();

        merchant.setMobNo(mobNo);

        restaurant.setImage(storageService.upload(image, "restaurant"));

        merchantService.createMerchant(merchant, restaurant);

        return jwtService.generateToken(mobNo, Role.merchant);
    }

    @Validated
    @PostMapping(value = "/merchant/login")
    @Transactional
    public String loadMerchant(){
        String mobNo = user.getMobNo();
        Merchant merchant = merchantService.getMerchant(mobNo);
        return jwtService.generateToken(mobNo,Role.merchant);
    }

    @GetMapping("/manager/invitations")
    @Validated
    @Transactional
    public List<OutletInvitation> viewInvitation(){
        String mobNo= user.getMobNo();
        return managerService.getInvitation(mobNo);
    }


    @GetMapping("/manager/invitation/accept")
    @Validated
    @Transactional
    public String acceptInvitation(@RequestParam("i")Integer invitationId) {
        String mobNo = user.getMobNo();
        Integer outletId = managerService.acceptInvitation(mobNo, invitationId);
        return jwtService.generateToken(mobNo, Role.manager, outletId);
    }



    @GetMapping("/manager/login")
    @Transactional
    @Validated
    public String managerLogin(){
        String mobNo = user.getMobNo();
        Integer outletId = managerService.findLinkedOutlet(mobNo);
        return jwtService.generateToken(mobNo, Role.manager, outletId);
    }


}
