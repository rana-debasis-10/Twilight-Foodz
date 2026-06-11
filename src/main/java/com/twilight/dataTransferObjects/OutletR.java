package com.twilight.dataTransferObjects;

import com.twilight.types.OutletStatus;

import java.io.Serializable;

public record OutletR (String outletId ,
                       String restaurantName,
                       String restaurantImage ,
                       OutletStatus outletStatus,
                       Double latitude,
                       Double longitude)
        implements Serializable{}
