package com.twilight.repositories;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<@NonNull Vehicle,@NonNull String> {
}
