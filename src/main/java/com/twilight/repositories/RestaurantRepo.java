package com.twilight.repositories;

import com.twilight.components.database.Restaurant;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RestaurantRepo extends JpaRepository<@NonNull Restaurant,@NonNull String> {
    @NonNull
    public Page<@NonNull Restaurant> findAll(@NonNull Pageable pageable);
    @NonNull
    public Page<@NonNull Restaurant> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
