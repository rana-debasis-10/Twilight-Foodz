package com.twilight.repositories;

import com.twilight.objects.Order;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderRepository extends JpaRepository<@NonNull Order,@NonNull Long> {
    Page<@NonNull Order> findByCustomerId(String customerId, Pageable pageable);
    @NonNull Page< @NonNull Order> findAll(@NonNull Pageable pageable);
}
