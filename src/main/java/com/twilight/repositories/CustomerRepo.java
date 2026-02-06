package com.twilight.repositories;

import com.twilight.components.database.Customer;
import org.jspecify.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<@NonNull Customer,@NonNull String> {
        @NonNull
        public Page<@NonNull Customer> findAll(@NonNull Pageable pageable);
        public Optional<Customer> findByEmail(String email);
}
