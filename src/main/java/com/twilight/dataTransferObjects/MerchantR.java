package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotNull;

public record MerchantR(
        @NotNull
        String email,
        @NotNull
        String name) {
}
