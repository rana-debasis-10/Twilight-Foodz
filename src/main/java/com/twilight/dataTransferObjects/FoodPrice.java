package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotNull;

public record FoodPrice(@NotNull Integer foodId , @NotNull Double price) {
}
