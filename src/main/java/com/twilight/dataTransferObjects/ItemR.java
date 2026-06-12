package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@NotNull
public record ItemR(@NotNull Integer foodId,@NotNull @Min(1) @Max(6) Integer quantity) {
}
