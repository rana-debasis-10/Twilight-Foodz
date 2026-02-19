package com.twilight.dataTransferObjects.authentication;

import com.twilight.annotations.ValidEmail;
import com.twilight.annotations.ValidMobileNumber;
import com.twilight.objects.database.Customer;

public record BasicUserDetails(String name, @ValidEmail String email, @ValidMobileNumber String mobNo, String password ) {
    public BasicUserDetails(Customer customer){
        this(customer.getName(), customer.getEmail(), customer.getMobNo(), customer.getPassword());
    }
}
