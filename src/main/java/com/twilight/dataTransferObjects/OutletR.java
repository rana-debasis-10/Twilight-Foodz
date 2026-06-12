package com.twilight.dataTransferObjects;

import com.twilight.types.OutletStatus;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record OutletR (
        @NotNull Integer outletId ,
        @NotNull
        String restaurantName,
        @NotNull
        String restaurantImage ,
        @NotNull
        OutletStatus outletStatus,
        @NotNull
        Double latitude,
        @NotNull
        Double longitude
)implements Serializable{}
