package com.twilight.repositories;

import com.twilight.dataTransferObjects.OutletDetailed;
import com.twilight.dataTransferObjects.OutletR;
import com.twilight.objects.Outlet;
import com.twilight.dataTransferObjects.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OutletRepository extends JpaRepository<Outlet,Integer> {
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
                    ST_MakePoint(:longitude,:lat),
                    4326
                    )
                    LIMIT :limit
                    """,
            nativeQuery = true)
    List<OutletR> findNearestOutlets(
            @Param("lat") double lat,
            @Param("longitude") double lon,
            @Param("limit") int limit
    );

    List<Outlet> findByRestaurantId(
            Integer restaurantId
    );

    @Query("""
                select new com.twilight.dataTransferObjects.Point(
                o.latitude,
                o.longitude
                )
                from Outlet as o
                
                where o.id = :outletId
                and o.outletStatus =  com.twilight.types.OutletStatus.open
                """)
    Point findLocationByIdAndStatus(
            @Param("outletId") Integer outletId
    );


    Optional<String> findIdByManagerMobNo(String managerMobNo);


    @Query("""
                SELECT new com.twilight.dataTransferObjects.OutletR(
                o.id AS outletId,
                r.name AS restaurantName,
                r.image AS restaurantImage,
                o.outletStatus AS outletStatus,
                o.latitude AS latitude,
                o.longitude AS longitude
                )
                FROM Outlet o
                JOIN o.restaurant r
                WHERE o.id = :outletId
    """)
    Optional<OutletR> findByOutletId(
            @Param("outletId") Integer outletId
    );
    @Query("""
                SELECT new com.twilight.dataTransferObjects.OutletDetailed(
                    o.id AS id,
                    r.name AS restaurantName,
                    r.image AS restaurantImage,
                    o.outletStatus AS outletStatus,
                    o.latitude AS latitude,
                    o.longitude AS longitude
                    o.managerMobNo AS managerMobNo
                )
                FROM Outlet o
                JOIN o.restaurant r
                WHERE o.merchantMobNo = :merchantMobNo
    """)
    List<OutletDetailed> findAllByMerchantMobNo(@Param("merchantMobNo")String merchantMobNo);
}
