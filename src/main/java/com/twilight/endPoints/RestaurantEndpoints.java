package com.twilight.endPoints;

import com.twilight.annotations.MobileNumber;
import com.twilight.dataTransferObjects.Address;

import com.twilight.dataTransferObjects.OutletDetailed;
import com.twilight.dataTransferObjects.OutletR;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.mappers.MerchantMapper;
import com.twilight.mappers.RestaurantMapper;
import com.twilight.dataTransferObjects.Point;
import com.twilight.objects.OutletInvitation;
import com.twilight.services.*;
import com.twilight.utils.UserContext;

import com.twilight.validators.ImageValidator;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantEndpoints {
    @Autowired
    private MerchantService merchantService;

    @Autowired
    UserContext userContext;

    @Autowired
    GeoCodingService geoCoding;


    @PostMapping("/create/outlet")
    @Transactional
    @Validated
    public void create(@RequestBody Address address) throws UnAuthorizedException, ChangeSetPersister.NotFoundException {
        String mobNo = userContext.getMobNo();
        Point point = geoCoding.getLocation(address);
        merchantService.createOutlet(mobNo,point);
    }
    @PostMapping("/outlet/invite")
    @Transactional
    @Validated
    public OutletInvitation inviteManager(@MobileNumber @RequestParam("m") String inviteeMobNo, @RequestParam("o") Integer outletId){
        String merchantMobNo = userContext.getMobNo();
        return merchantService.inviteManager(merchantMobNo,inviteeMobNo,outletId);
    }
    @PostMapping("/outlet/invite/other")
    @Transactional
    @Validated
    public OutletInvitation inviteOtherManager(
            @MobileNumber @RequestParam("m") String inviteeMobNo,
            @RequestParam("o") Integer outletId,
            @RequestParam("i")Integer invitationId
    ) throws BadRequestException {
        String merchantMobNo = userContext.getMobNo();
        return merchantService.inviteOtherManager(merchantMobNo,inviteeMobNo,outletId,invitationId);
    }
    @PostMapping("/outlet/viewAll")
    @Transactional
    @Validated
    public List<OutletDetailed> viewAllOutlets(){
        String merchantMobNo = userContext.getMobNo();
        return  merchantService.viewAllOutlets(merchantMobNo);
    }
}
