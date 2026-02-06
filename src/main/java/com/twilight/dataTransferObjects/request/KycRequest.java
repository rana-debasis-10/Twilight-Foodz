package com.twilight.dataTransferObjects.request;

import jakarta.validation.constraints.NotNull;
public record KycRequest(@NotNull String pan, @NotNull String aadhaar, @NotNull String bankAccount, @NotNull String ifsc) {
}