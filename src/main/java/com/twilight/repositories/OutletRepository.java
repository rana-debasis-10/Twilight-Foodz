package com.twilight.repositories;

import com.twilight.dataTransferObjects.OutletR;
import com.twilight.objects.Outlet;
import com.twilight.objects.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OutletRepository extends JpaRepository<Outlet,String> {

        @Query(value = """
    SELECT
        o.id AS outletId,
        r.name AS restaurantName,
        r.image AS restaurantImage,
        o.outlet_status AS outletStatus,
        o.latitude as latitude,
        o.longitude as longitude
    FROM outlet o
    JOIN restaurant r
        ON o.restaurant_id = r.id
    ORDER BY ST_SetSRID(
                ST_MakePoint(o.longitude, o.latitude),
                4326
             ) <-> ST_SetSRID(
                ST_MakePoint(:lon,:lat),
                4326
             )
    LIMIT :limit
    """,
                nativeQuery = true)
        List<OutletR> findNearestOutlets(
                @Param("lat") double lat,
                @Param("lon") double lon,
                @Param("limit") int limit
        );

        List<Outlet> findByRestaurantId(String restaurantId);

        @Query("""
                select new com.twilight.objects.Point(
                        o.latitude,
                        o.longitude
                )
                from Outlet as o
                
                where o.id = :outletId
                and o.outletStatus =  com.twilight.types.OutletStatus.open
                """)
        Point findLocationByIdAndStatus(@Param("outletId") String outletId);


    Optional<String> findByManager(String manager);
}
