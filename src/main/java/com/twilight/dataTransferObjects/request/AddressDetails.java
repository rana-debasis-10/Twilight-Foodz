package com.twilight.dataTransferObjects.request;

import com.twilight.components.database.Address;

public record AddressDetails(String street, String city, String state, String country, String postalCode, String landMark){

    public AddressDetails(Address address ){
            this(address.getStreet(), address.getCity(), address.getState(), address.getCountry(), address.getPostalCode(), address.getLandMark());
    }

}
