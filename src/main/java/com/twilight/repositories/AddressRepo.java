package com.twilight.repositories;

import com.twilight.components.database.Address;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepo extends JpaRepository<@NonNull Address,@NonNull Long> {
    @NonNull Page<@NonNull Address> findAll(@NonNull Pageable pageable);

    @NonNull List<Address> findAllByPostalCode(@NonNull String postalCode);
}
