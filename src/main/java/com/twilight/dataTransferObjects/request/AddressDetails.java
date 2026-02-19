package com.twilight.dataTransferObjects.request;

import com.twilight.objects.database.Location;

public record AddressDetails(String street, String city, String state, String country, String postalCode, String landMark){

    public AddressDetails(Location location){
            this(location.getStreet(), location.getCity(), location.getState(), location.getCountry(), location.getPostalCode(), location.getLandMark());
    }

}
