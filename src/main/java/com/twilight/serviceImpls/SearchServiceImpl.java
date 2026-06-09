package com.twilight.serviceImpls;

import com.twilight.dataTransferObjects.FoodR;
import com.twilight.dataTransferObjects.OutletR;
import com.twilight.objects.Point;
import com.twilight.repositories.FoodRepository;
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

    @Autowired
    FoodRepository foodRepository;
    @Override
    @Cacheable("outlets")
    public List<OutletR> findNearestOutlets(
            Double lat,
            Double lon) {

        return outletRepository
                .findNearestOutlets(lat, lon, 300)
                .stream()
                .filter(outlet ->
                        distanceKm(
                                lat,
                                lon,
                                outlet.latitude(),
                                outlet.longitude()
                        ) <= 5.0
                )
                .toList();
    }

    @Override
    public List<FoodR> getFoods(String outletId) {
        return foodRepository.findMenuByOutletId(outletId);
    }

    public static double distanceKm(
            double lat1,
            double lon1,
            double lat2,
            double lon2) {

        final double EARTH_RADIUS = 6371.0;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2)
                        + Math.cos(lat1)
                        * Math.cos(lat2)
                        * Math.sin(dLon / 2)
                        * Math.sin(dLon / 2);

        double c =
                2 * Math.atan2(
                        Math.sqrt(a),
                        Math.sqrt(1 - a)
                );

        return EARTH_RADIUS * c;
    }
    public boolean isDeliverable(Double lat , Double lon, String outletId){
        Point location = outletRepository.findLocationByIdAndStatus(outletId);
        return distanceKm(lat,lon,location.lat(),location.lon()) <= 5.0;
    }
}
