package com.twilight.repositories;

import com.twilight.dataTransferObjects.FoodPrice;
import com.twilight.dataTransferObjects.FoodR;
import com.twilight.objects.Food;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<@NonNull Food,@NonNull String> {
    @Query("""
    SELECT new com.twilight.dataTransferObjects.FoodR(
        f.id,
        p.name,
        p.image,
        f.priceOverride,
        f.available
    )
    FROM Food f
    JOIN f.product p
    WHERE f.outlet.id = :outletId
      AND f.available = true
""")
    List<FoodR> findMenuByOutletId(
            @Param("outletId") String outletId
    );
    @Query("""
    SELECT new com.twilight.dataTransferObjects.FoodPrice(
        f.id,
        COALESCE(f.priceOverride, f.product.price)
    )
    FROM Food f
    WHERE f.outlet.id = :outletId
      AND f.id IN :foodIds
      AND f.available = true
""")
    List<FoodPrice> findFoodPrices(
            String outletId,
            List<String> foodIds
    );

    Optional<Food> findFoodByIdAndOutletId(String foodId, String outletId);
}



