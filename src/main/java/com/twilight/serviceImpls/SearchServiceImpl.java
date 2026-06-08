package com.twilight.serviceImpls;

import com.twilight.objects.Outlet;
import com.twilight.repositories.OutletRepository;
import com.twilight.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    OutletRepository outletRepository;
    @Override
    @Cacheable("outlets")
    public List<Outlet> findNearestOutlets(Double lat, Double lon) {
        return outletRepository.findNearestOutlets(lat,lon,300);
    }
}
