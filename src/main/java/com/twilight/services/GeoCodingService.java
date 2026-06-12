package com.twilight.services;


import com.twilight.dataTransferObjects.Address;
import com.twilight.dataTransferObjects.Point;
import com.twilight.exceptions.GeocodingError;
import jakarta.validation.constraints.NotNull;

public interface GeoCodingService {
    Point getLocation(@NotNull Address address) throws GeocodingError;
}
