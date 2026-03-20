package com.twilight.dataTransferObjects;

import com.twilight.objects.Address;

public record AddressR(String street, String city, String state, String country, String postalCode, String landMark){

    public AddressR(Address address){
            this(address.getStreet(), address.getCity(), address.getState(), address.getCountry(), address.getPostalCode(), address.getLandMark());
    }

}
