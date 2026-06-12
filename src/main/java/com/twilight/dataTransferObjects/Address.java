package com.twilight.dataTransferObjects;


import jakarta.validation.constraints.NotNull;

public record Address(
        @NotNull
        String state,
        @NotNull
        String city,
        @NotNull
        String pinCode,
        String street,
        String landMark
){}
