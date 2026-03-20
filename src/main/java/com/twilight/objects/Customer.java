package com.twilight.objects;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class Customer {
    @NotNull(message = "Enter your name")
    private String name;
    private Boolean addressAdded;
    @OneToOne
    @JoinColumn(name = "addressId")
    private Address primaryAddress;
    @OneToMany(mappedBy = "customer")
    private List<Address> allAddresses;

}
