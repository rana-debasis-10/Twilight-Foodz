package com.twilight.dataTransferObjects;

import com.twilight.annotations.MobileNumber;
import com.twilight.types.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record OrderR(@NotNull List<ItemR> foods, AddressR addressR, @NotNull boolean defaultMobNo, @MobileNumber String mobNo, @NotNull Currency currency, @NotNull PaymentMethod paymentMethod){}
