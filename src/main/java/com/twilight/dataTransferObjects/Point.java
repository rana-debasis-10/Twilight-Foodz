package com.twilight.dataTransferObjects;


import jakarta.validation.constraints.NotNull;

public record Point(@NotNull Double latitude, @NotNull Double longitude){}
