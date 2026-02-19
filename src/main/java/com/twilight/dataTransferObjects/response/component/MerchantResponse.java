package com.twilight.dataTransferObjects.response.component;

import com.twilight.objects.database.Customer;
import com.twilight.objects.database.Merchant;

public record MerchantResponse(String id,String name, String mobNo, String email, String aadhaar , String pan , String bankAccount, String ifsc) {
    public MerchantResponse(Customer customer, Merchant merchant){
        this(customer.getId(),customer.getName(),customer.getMobNo(), customer.getEmail(),merchant.getAadhaar(),merchant.getPan(),merchant.getBankAccount(),merchant.getIfsc());
    };
}
