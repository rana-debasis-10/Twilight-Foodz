package com.twilight.services;

import com.twilight.dataTransferObjects.FoodR;
import com.twilight.dataTransferObjects.OutletR;


import java.util.List;

public interface SearchService {
    public List<OutletR> findNearestOutlets(Double lat, Double lon);
    public List<FoodR> getFoods(Integer outletId);
}
