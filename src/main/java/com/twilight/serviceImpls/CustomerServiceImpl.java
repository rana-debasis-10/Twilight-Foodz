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
import com.twilight.types.InvitationStatus;
import com.twilight.types.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OutletInvitationRepository outletInvitationRepository;

    @Autowired
    JwtService jwtService;


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
            return true;}


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

    @Override
    public String acceptInvitation(String mobNo, Integer invitationId) {
        OutletInvitation invitation =outletInvitationRepository.findByIdAndInviteeMobNo(invitationId,mobNo);
        if(invitation==null){
            throw new UnauthorizedException("Invitation does not exist or exists but not for you");
        }
        outletInvitationRepository.delete(invitation);
       return jwtService.generateToken(mobNo, Role.manager,invitation.getOutletId());
    }

    @Override
    public List<OutletInvitation> getALlInvitation(String mobNo) {
        return outletInvitationRepository.findAllByInviteeMobileNo(mobNo);
    }
}
