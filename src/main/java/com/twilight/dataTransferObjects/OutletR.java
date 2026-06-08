package com.twilight.dataTransferObjects;

import com.twilight.types.OutletStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;

public record OutletR (String outletId ,String restaurantName, String restaurantImage ,OutletStatus outletStatus) implements Serializable{}
