package com.twilight.repositories;

import com.twilight.objects.database.Product;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepo extends JpaRepository< @NonNull Product,@NonNull String> {
    @NonNull
    public Page<@NonNull Product> findAll(@NonNull Pageable pageable);
    public Page<@NonNull Product> findAllByRestaurantId(@NonNull String restaurantId, @NonNull Pageable pageable);
    public Page<@NonNull Product> findByNameContainingIgnoreCase(String name , @NonNull Pageable pageable);
    public Page<@NonNull Product> findByRestaurantIdAndNameContainingIgnoreCase(@NonNull String restaurantId, @NonNull String name, @NonNull Pageable pageable);
    public Optional<@NonNull Product> findByIdAndRestaurantId(@NonNull String id , @NonNull String restaurantId);
    public void deleteByIdAndRestaurantId(@NonNull String id ,@NonNull String restaurantId);
    List<@NonNull Product> findByIdIn(@NonNull List<String> ids);

}
