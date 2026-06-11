package com.twilight.apis;

import com.twilight.dataTransferObjects.OutletR;
import com.twilight.exceptions.UnauthorizedException;
import com.twilight.objects.OutletInvitation;
import com.twilight.services.JwtService;
import com.twilight.services.ManagerService;
import com.twilight.types.Role;
import com.twilight.utils.UserContext;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/manager")
public class ManagerEndpoints {
    @Autowired
    UserContext userContext;

    @Autowired
    ManagerService managerService;

    @Autowired
    JwtService jwtService;

    @GetMapping("/login")
    @Transactional
    @Validated
    public String managerLogin() throws ChangeSetPersister.NotFoundException {
        String mobNo= userContext.getMobile_Number();
        String outletId= managerService.findLinkedOutlet(mobNo);
        return jwtService.generateToken(mobNo, Role.manager, outletId);
    }

    @Transactional
    @GetMapping("/invitation/show")
    @Validated
    OutletInvitation showInvitations() throws ChangeSetPersister.NotFoundException {
        String mobNo= userContext.getMobile_Number();
        return managerService.getInvitation(mobNo);
    }
    @GetMapping("/invitation/accept")
    @Validated
    String acceptInvitation(@RequestParam(name="i")Integer invitationId) throws ChangeSetPersister.NotFoundException {
        String mobNo = userContext.getMobile_Number();
        String outletId = managerService.acceptInvitation(mobNo,invitationId);
        return jwtService.generateToken(mobNo, Role.manager, outletId);
    }
    @GetMapping("/update/food/price")
    void updateFoodPrice(String outletId, String foodId, Double price)
            throws ChangeSetPersister.NotFoundException{
        managerService.updateFoodPrice(outletId,foodId,price);
    };

    @GetMapping("/update/food/available")
    void makeFoodAvailable(String outletId, String foodId) throws ChangeSetPersister.NotFoundException{
        managerService.makeFoodAvailable(outletId,foodId);
    };

    @GetMapping("/update/food/unavailable")
    void makeFoodUnavailable(String outletId, String foodId) throws ChangeSetPersister.NotFoundException{
        managerService.makeFoodUnavailable(outletId,foodId);
    };

    @GetMapping("/update/outlet/open")
    void openOutlet(String outletId)throws ChangeSetPersister.NotFoundException{
        managerService.openOutlet(outletId);
    };

    @GetMapping("/update/outlet/close")
    void closeOutlet(String outletId)throws ChangeSetPersister.NotFoundException{
        managerService.closeOutlet(outletId);
    };
    @GetMapping("/view/outlet")
    OutletR viewOutlet() throws BadRequestException {
        String outletId = userContext.getEstablishment();
        if(outletId==null)
            throw new UnauthorizedException("Outlet Should be present");
        return managerService.viewOutlet(outletId);
    }
}

