package com.twilight.repositories;

import com.twilight.objects.Food;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FoodRepository extends JpaRepository<@NonNull Food,@NonNull String> {
}



