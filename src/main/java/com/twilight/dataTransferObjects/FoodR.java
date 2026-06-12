package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotNull;

public record FoodR(
        @NotNull
        Integer foodId,
        @NotNull
        String name,
        @NotNull
        String image,
        @NotNull
        Double price,
        @NotNull
        boolean available
) {}