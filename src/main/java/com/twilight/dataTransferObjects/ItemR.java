package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@NotNull
public record ItemR(@NotNull String foodId, @Min(1) @Max(6) Integer quantity) {
}
