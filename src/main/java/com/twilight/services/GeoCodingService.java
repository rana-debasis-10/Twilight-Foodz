package com.twilight.services;


import com.twilight.dataTransferObjects.AddressR;
import com.twilight.dataTransferObjects.Point;

public interface GeoCodingService {
    Point getLocation(AddressR address);
}
