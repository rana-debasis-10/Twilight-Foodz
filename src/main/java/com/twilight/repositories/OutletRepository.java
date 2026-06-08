package com.twilight.repositories;

import com.twilight.objects.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OutletRepository extends JpaRepository<Outlet,String> {

        @Query(value = """
            SELECT *
            FROM outlet
            ORDER BY ST_SetSRID(
                        ST_MakePoint(longitude, latitude),
                        4326
                     ) <-> ST_SetSRID(
                        ST_MakePoint(:lon,:lat),
                        4326
                     )
            LIMIT :limit
            """,nativeQuery = true)
        List<Outlet> findNearestOutlets(
                @Param("lat") double lat,
                @Param("lon") double lon,
                @Param("limit") int limit
        );

}
