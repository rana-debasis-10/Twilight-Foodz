package com.twilight.repositories;

import com.twilight.objects.database.Food;
import com.twilight.dataTransferObjects.response.component.FoodResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

public interface OutletFoodRepo extends JpaRepository<@NonNull Food,@NonNull String> {

    @Query("""
    SELECT new com.twilight.dataTransferObjects.response.component.FoodResponse(
        op.id,
        f.name,
        f.image,
        op.priceOverride,
        op.available,
        f.description
    )
    FROM Food op
    JOIN op.product f
    WHERE op.outlet = :outletId
    AND op.available = true
    AND LOWER(f.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<FoodResponse> searchByOutletId(@NonNull @Param("outletId") String outletId , @NonNull @Param("keyword") String keyword, @NonNull Pageable pageable);

    @Query("""
    SELECT new com.twilight.dataTransferObjects.response.component.FoodResponse(
        op.id,
        f.name,
        f.image,
        op.priceOverride,
        op.available,
        f.description
    )
    FROM Food op
    JOIN op.product f
    WHERE op.available = true
    AND LOWER(f.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
    """)
    List<FoodResponse> search( @NonNull @Param("keyword") String keyword,@NonNull Pageable pageable);

    @Query("""
        SELECT new com.twilight.dataTransferObjects.response.component.FoodResponse(
            op.id,
            f.name,
            f.image,
            op.priceOverride,
            op.available,
            f.description
        )
        FROM Food op
        JOIN op.product f
        WHERE op.available = true
        AND op.id IN :outletFoodIds
    """)
    List<FoodResponse> findAllByIds(@Param("outletFoodIds") List<String> outletFoodIds);

}



