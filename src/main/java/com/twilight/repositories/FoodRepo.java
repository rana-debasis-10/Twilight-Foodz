package com.twilight.repositories;

import com.twilight.components.database.Food;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepo extends JpaRepository< @NonNull Food,@NonNull String> {
    @NonNull
    public Page<@NonNull Food> findAll(@NonNull Pageable pageable);
    public Page<@NonNull Food> findAllByRestaurantId(@NonNull String restaurantId,@NonNull Pageable pageable);
    public Page<@NonNull Food> findByNameContainingIgnoreCase(String name , @NonNull Pageable pageable);
    public Page<@NonNull Food> findByRestaurantIdAndNameContainingIgnoreCase(@NonNull String restaurantId, @NonNull String name,@NonNull Pageable pageable);
    public Optional<@NonNull Food> findByIdAndRestaurantId(@NonNull String id ,@NonNull String restaurantId);
    public void deleteByIdAndRestaurantId(@NonNull String id ,@NonNull String restaurantId);
    List<@NonNull Food> findByIdIn(@NonNull List<String> ids);

}
