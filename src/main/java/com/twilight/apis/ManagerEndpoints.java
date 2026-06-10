package com.twilight.apis;

import com.twilight.objects.OutletInvitation;
import com.twilight.services.ManagerService;
import com.twilight.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerEndpoints {
    @Autowired
    UserContext userContext;

    @Autowired
    ManagerService managerService;

    @GetMapping("/login")
    public String managerLogin(){
        return null;
    }
    @GetMapping("/invitation/show")
    OutletInvitation showInvitations() throws ChangeSetPersister.NotFoundException {
        String mobNo= userContext.getMobile_Number();
        return managerService.getInvitation(mobNo);
    }}
