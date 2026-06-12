package com.twilight.services;

import com.twilight.annotations.MobileNumber;
import com.twilight.objects.Customer;
import com.twilight.objects.CustomerAddress;
import com.twilight.types.AddressType;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;

public interface CustomerService {
    Customer load(@NotNull @MobileNumber String mobNo);
    void create(@NotNull @MobileNumber String mobNo, @NotNull String name);
    void addAddressAsType(@NonNull Customer customer, @NotNull CustomerAddress address);
}
