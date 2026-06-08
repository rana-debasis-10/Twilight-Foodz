package com.twilight.services;

import com.twilight.objects.Customer;
import com.twilight.objects.CustomerAddress;
import com.twilight.objects.OutletInvitation;

import java.util.List;

public interface CustomerService {
    Customer loadCustomer(String mobNo);
    boolean createCustomer(String mobNo,String name);
    void addAddress(String mobNo,CustomerAddress address);
    String acceptInvitation(String mobNo,Integer invitationId);
    public List<OutletInvitation> getALlInvitation(String mobNo);

}
