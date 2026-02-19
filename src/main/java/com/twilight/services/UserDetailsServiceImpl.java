package com.twilight.services;

import org.jspecify.annotations.NonNull;
import com.twilight.objects.database.Customer;
import com.twilight.objects.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerService customerService;

    @Autowired
    public UserDetailsServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    @NonNull
    public UserDetailsImpl loadUserByUsername(@NonNull String id) throws UsernameNotFoundException {
        Customer customer = customerService.getById(id);
        if(customer != null){
            return new UserDetailsImpl(customer);
        }
        else{
            throw new UsernameNotFoundException("Invalid Credentials");
        }
    }
}
