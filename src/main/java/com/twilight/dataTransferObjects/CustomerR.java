package com.twilight.dataTransferObjects;

import com.twilight.annotations.MobileNumber;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CustomerR(
        @NotNull @MobileNumber String mobNo,
        String name,
        List<Address> addresses
) {}
