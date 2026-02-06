package com.twilight.services;

import com.twilight.components.security.UserDetailsImpl;
import com.twilight.dataTransferObjects.authentication.BasicUserDetails;
import com.twilight.components.database.Customer;
import com.twilight.dataTransferObjects.authentication.LoginDetails;
import com.twilight.exceptions.UserAlreadyExists;
import com.twilight.repositories.MerchantRepo;
import com.twilight.types.Role;
import com.twilight.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;


    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    /// For Super Admin
    @Transactional
    public List<BasicUserDetails> getAll(int page) {
        return customerRepo.findAll(PageRequest.of(page,10))
                .getContent()
                .stream()
                .map(BasicUserDetails::new)
                .toList();
    }

    @Transactional
    /// For Super Admin
    public void disableAccount(String id){
        Customer customer = getById(id);
        customer.setEnabled(false);
    }

    @Transactional
    /// For Making Super Admin
    public void superAdmin(String id) throws UsernameNotFoundException{
        Customer customer = getById(id);
        customer.setRole(Role.SUPER_ADMIN);
        update(customer);
    }

    @Transactional
    /// For Internal Uses
    public Customer getById(String id) throws UsernameNotFoundException {
        if(customerRepo.findById(id).isPresent()){
            return customerRepo.findById(id).get();
        }
        else{
            throw new UsernameNotFoundException(id);
        }
    }

    @Transactional
    /// For External Uses
    public BasicUserDetails get(String id) throws UsernameNotFoundException {
        if(customerRepo.findById(id).isPresent()){
            return new BasicUserDetails(customerRepo.findById(id).get());
        }
        else{
            throw new UsernameNotFoundException(id);
        }
    }


    @Transactional
    protected Customer save(BasicUserDetails basicUserDetails)throws UserAlreadyExists {
        try{
            return customerRepo.save(new Customer(basicUserDetails));
        }
        catch (Exception e){
            throw new UserAlreadyExists(basicUserDetails.email());
        }
    }

    @Transactional
    /// For Update the Customer
    public void update(Customer customer){
        customerRepo.save(customer);
    }
}
