package com.twilight.dataTransferObjects;

import com.twilight.annotations.MobileNumber;
import com.twilight.objects.Food;
import com.twilight.objects.Restaurant;
import com.twilight.types.OutletStatus;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OutletDetailed(
        Integer id,
        String restaurantName,
        @NotNull
        String restaurantImage,
        OutletStatus outletStatus,
        @NotNull Double longitude,
        @NotNull Double latitude,
        String managerMobNo
){}
