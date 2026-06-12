package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotNull;

public record Webhook(
        @NotNull String payload,
        @NotNull String signature)
{}
