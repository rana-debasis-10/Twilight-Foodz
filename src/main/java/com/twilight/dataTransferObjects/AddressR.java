package com.twilight.dataTransferObjects;


public record AddressR(
        Long id,
        String state,
        String city,
        String pinCode,
        String street,
        String landMark){

}
