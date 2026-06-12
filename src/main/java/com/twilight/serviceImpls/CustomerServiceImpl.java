package com.twilight.serviceImpls;

import com.twilight.exceptions.NotFoundException;
import com.twilight.exceptions.SqlException;
import com.twilight.exceptions.UnAuthorizedException;
import com.twilight.objects.Customer;
import com.twilight.objects.CustomerAddress;
import com.twilight.repositories.CustomerAddressRepository;
import com.twilight.repositories.CustomerRepository;
import com.twilight.services.CustomerService;

import com.twilight.types.AddressType;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerAddressRepository customerAddressRepository;



    @Override
    public Customer load(String mobNo) throws UnAuthorizedException {
        return customerRepository.findById(mobNo).orElseThrow(()->new NotFoundException("Not registered"));
    }

    @Override
    public void create(String mobNo, String name) throws UnAuthorizedException, SqlException {
        customerRepository.findById(mobNo)
                .ifPresent(customer ->{
                        throw new UnAuthorizedException("User already exists");
                    }
                );
        Customer customer = new Customer();
        customer.setName(name);
        customer.setMobNo(mobNo);
        try{
            customerRepository.save(customer);
        } catch (Exception e) {
            throw new SqlException(e.getMessage());
        }
    }

    @Override
    public void addAddressAsType(@NonNull Customer customer, @NotNull CustomerAddress address) {
        address.setCustomer(customer);
        try
        {
            customerAddressRepository.save(address);
        } catch (RuntimeException e) {
            throw new SqlException(e.getMessage());
        }
    }


}
