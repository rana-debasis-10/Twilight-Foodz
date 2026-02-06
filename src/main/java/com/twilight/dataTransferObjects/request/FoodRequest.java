package com.twilight.dataTransferObjects.request;

import jakarta.validation.constraints.NotNull;

public record FoodRequest(@NotNull String name, @NotNull Double price, @NotNull String description){
}
