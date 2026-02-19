package com.twilight.repositories;

import com.twilight.objects.database.Location;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepo extends JpaRepository<@NonNull Location,@NonNull Long> {
    @NonNull Page<@NonNull Location> findAll(@NonNull Pageable pageable);

    @NonNull List<Location> findAllByPostalCode(@NonNull String postalCode);
}
