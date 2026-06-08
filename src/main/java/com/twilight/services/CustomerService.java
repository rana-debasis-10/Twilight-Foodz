package com.twilight.services;

import com.twilight.objects.Customer;
import com.twilight.objects.CustomerAddress;

public interface CustomerService {
    Customer loadCustomer(String mobNo);
    boolean createCustomer(String mobNo,String name);
    void addAddress(String mobNo,CustomerAddress address);
    void acceptInvitation(String mobNo,String invitationId);
}
