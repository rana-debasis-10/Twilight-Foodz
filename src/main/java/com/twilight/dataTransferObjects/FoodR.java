package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotNull;

public record FoodR(@NotNull String name, @NotNull Double price, @NotNull String description){
}
