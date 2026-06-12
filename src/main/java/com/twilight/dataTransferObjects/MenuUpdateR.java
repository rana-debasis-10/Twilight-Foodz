package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record MenuUpdateR(
        @NotNull Integer productId,
        Integer restaurantId
)implements Serializable {}
