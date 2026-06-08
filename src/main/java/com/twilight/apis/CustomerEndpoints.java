package com.twilight.apis;

import com.twilight.dataTransferObjects.AddressR;
import com.twilight.dataTransferObjects.CustomerR;
import com.twilight.dataTransferObjects.Name;
import com.twilight.mappers.CustomerAddressMapper;
import com.twilight.mappers.CustomerMapper;
import com.twilight.objects.Outlet;
import com.twilight.objects.OutletInvitation;
import com.twilight.services.CustomerService;
import com.twilight.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerEndpoints {
    @Autowired
    CustomerService service;
    @Autowired
    CustomerMapper customerMapper;
    @Autowired
    UserContext userContext;
    @GetMapping("/load")
    Optional<CustomerR> loadCustomer(){
        String mobNo = userContext.getMobile_Number();
        return Optional.ofNullable(customerMapper.toCustomerR(service.loadCustomer(mobNo)));
    }
    @PostMapping("/create")
    boolean create(@RequestBody(required = true) Name name ){
        String mobNo = userContext.getMobile_Number();
        return service.createCustomer(mobNo,name.name());
    }
    @GetMapping("/invitations/accept")
    String acceptInvitation(@RequestParam(name ="i") Integer invitationId){
        String mobNo= userContext.getMobile_Number();
        return service.acceptInvitation(mobNo,invitationId);
    }
    List<OutletInvitation> showInvitations(){
        String mobNo= userContext.getMobile_Number();
        return service.getALlInvitation(mobNo);
    }

}
