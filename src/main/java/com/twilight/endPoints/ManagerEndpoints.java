package com.twilight.endPoints;

import com.twilight.dataTransferObjects.FoodR;
import com.twilight.dataTransferObjects.OutletR;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.services.JwtService;
import com.twilight.services.ManagerService;
import com.twilight.types.Role;
import com.twilight.utils.UserContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping("/manager")
public class ManagerEndpoints {
    @Autowired
    UserContext user;

    @Autowired
    ManagerService managerService;

    @Autowired
    JwtService jwtService;



    @GetMapping("/update/food/price")
    @Validated
    @Transactional
    void updateFoodPrice(@RequestParam Integer foodId, Double price) {
        Integer outletId = (Integer)user.getCredential();
        if(outletId== null)
            throw new UnAuthorizedException(
                    "User trying unautherized outlet access",
                    "No Linked Outlet");
        managerService.updateFoodPrice(outletId,foodId,price);
    };

    @GetMapping("/update/food/available")
    @Validated
    @Transactional
    void makeFoodAvailable(@RequestParam Integer foodId) throws ChangeSetPersister.NotFoundException{
        Integer outletId = (Integer) user.getCredential();
        if(outletId== null)
            throw new UnAuthorizedException(
                    "User trying unautherized outlet access"
                    ,"No Linked Outlet");
        managerService.makeFoodAvailable(outletId,foodId);
    };

    @GetMapping("/update/food/unavailable")
    void makeFoodUnavailable(@RequestParam(required = true) Integer foodId) throws ChangeSetPersister.NotFoundException{
        Integer outletId = (Integer)user.getCredential();
        if(outletId== null)
            throw new UnAuthorizedException(
                    "User trying unautherized outlet access"
                    ,"No Linked Outlet");
        managerService.makeFoodUnavailable(outletId,foodId);
    };

    @GetMapping("/update/outlet/open")
    void openOutlet()throws ChangeSetPersister.NotFoundException{
        Integer outletId = (Integer) user.getCredential();
        if(outletId== null)
            throw new UnAuthorizedException(
                    "User trying unautherized outlet access"
                    ,"No Linked Outlet");
        managerService.openOutlet(outletId);
    };

    @GetMapping("/update/outlet/close")
    void closeOutlet()throws ChangeSetPersister.NotFoundException{
        Integer outletId = (Integer) user.getCredential();
        if(outletId== null)
            throw new UnAuthorizedException(
                    "User trying unautherized outlet access"
                    ,"No Linked Outlet");
        managerService.closeOutlet(outletId);
    };
    @GetMapping("/view/outlet")
    OutletR viewOutlet() throws BadRequestException {
        Integer outletId = (Integer) user.getCredential();
        if(outletId==null)
            throw new UnAuthorizedException(
                    "User trying unautherized outlet access"
                    ,"No Linked Outlet");
        return managerService.viewOutlet(outletId);
    }
    @GetMapping
    List<FoodR> viewAllFoods(){
        Integer outletId = (Integer) user.getCredential();
        if(outletId==null)
            throw new UnAuthorizedException(
                    "User trying unautherized outlet access"
                    ,"No Linked Outlet");
        return managerService.getAllFoods(outletId);
    }
}

