package com.twilight.repositories;

import com.twilight.dataTransferObjects.OutletR;
import com.twilight.objects.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OutletRepository extends JpaRepository<Outlet,String> {

        @Query(value = """
    SELECT
        o.id AS outletId,
        r.name AS restaurantName,
        r.image AS restaurantImage,
        o.outlet_status AS outletStatus
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

}
