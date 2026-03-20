package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotNull;

public record RestaurantR(@NotNull String name, @NotNull AddressR addressR){
}
