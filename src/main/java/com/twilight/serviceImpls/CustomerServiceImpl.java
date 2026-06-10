package com.twilight.serviceImpls;

import com.twilight.exceptions.UnauthorizedException;
import com.twilight.exceptions.UserAlreadyExists;
import com.twilight.objects.Customer;
import com.twilight.objects.CustomerAddress;
import com.twilight.objects.OutletInvitation;
import com.twilight.repositories.CustomerRepository;
import com.twilight.repositories.OutletInvitationRepository;
import com.twilight.services.CustomerService;
import com.twilight.services.JwtService;

import com.twilight.types.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;



    @Override
    public Customer loadCustomer(String mobNo) {
        Optional<Customer> customer = customerRepository.findById(mobNo);
        return customer.orElse(null);
    }

    @Override
    public boolean createCustomer(String mobNo,String name) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setMobNo(mobNo);
        if(customerRepository.findById(mobNo).isPresent())
            throw new UserAlreadyExists("User Exists");
        else{
            customerRepository.save(customer);
            return true;
        }
    }

    @Override
    public void addAddress(String mobNo,CustomerAddress address) {
        Optional<Customer> customer = customerRepository.findById(mobNo);
        if(customer.isPresent()){
            Customer customer1 = customer.get();
            customer1.getAddresses().add(address);
            address.setCustomer(customer1);
            customerRepository.save(customer1);
        }
        else
            throw new UnauthorizedException("User not found");
    }


}
