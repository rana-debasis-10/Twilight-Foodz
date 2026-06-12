package com.twilight.repositories;

import com.twilight.annotations.MobileNumber;
import com.twilight.objects.Restaurant;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<@NonNull Restaurant,@NonNull Integer> {
   Optional<Restaurant> findByMerchantMobNo(@MobileNumber String mobNo);
}
