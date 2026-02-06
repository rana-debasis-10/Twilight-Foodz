package com.twilight.dataTransferObjects.request;

import com.twilight.annotations.ValidMobileNumber;
import com.twilight.types.Currency;
import com.twilight.types.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record CustomerOrderRequest (@NotNull List<ItemRequest> foods, AddressDetails addressDetails, @NotNull boolean defaultMobNo, @ValidMobileNumber String mobNo, @NotNull Currency currency, @NotNull PaymentMethod paymentMethod){}
