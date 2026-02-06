package com.twilight.dataTransferObjects.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@NotNull
public record ItemRequest(@NotNull String foodId, @Min(1)Integer quantity) {
}
