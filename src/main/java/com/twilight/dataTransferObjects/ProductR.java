package com.twilight.dataTransferObjects;

import jakarta.validation.constraints.NotNull;

public record ProductR(
        @NotNull
        String name,
        @NotNull
        Double price ,
        @NotNull
        String imageFileName
){}
