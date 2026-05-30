package com.twilight.dataTransferObjects;

import com.twilight.annotations.MobileNumber;
import com.twilight.types.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record OrderR(@NotNull List<ItemR> foods, AddressR addressR, @MobileNumber String mobNo, @NotNull PaymentMethod paymentMethod){}
