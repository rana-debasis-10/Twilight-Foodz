package com.twilight.apis;

import com.twilight.dataTransferObjects.AddressR;
import com.twilight.dataTransferObjects.CustomerR;
import com.twilight.dataTransferObjects.Name;
import com.twilight.mappers.CustomerAddressMapper;
import com.twilight.mappers.CustomerMapper;
import com.twilight.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    CustomerAddressMapper customerAddressMapper;
    @GetMapping("/load")
    Optional<CustomerR> loadCustomer(){
        String mobNo = (String) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
        return Optional.ofNullable(customerMapper.toCustomerR(service.loadCustomer(mobNo)));
    }
    @PostMapping("/create")
    boolean create(@RequestBody(required = true) Name name ){
        String mobNo = (String) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
        return service.createCustomer(mobNo,name.name());
    }

}
