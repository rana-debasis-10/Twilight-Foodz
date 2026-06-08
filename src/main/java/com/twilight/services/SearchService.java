package com.twilight.services;

import com.twilight.objects.Outlet;

import java.util.List;

public interface SearchService {
    public List<Outlet> findNearestOutlets(Double lat, Double lon);
}
