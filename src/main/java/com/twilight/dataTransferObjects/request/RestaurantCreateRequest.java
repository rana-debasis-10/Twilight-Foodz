package com.twilight.dataTransferObjects.request;

import jakarta.validation.constraints.NotNull;

public record RestaurantCreateRequest (@NotNull String name, @NotNull AddressDetails addressDetails){
}
