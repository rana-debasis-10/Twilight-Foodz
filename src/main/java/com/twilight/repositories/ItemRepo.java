package com.twilight.repositories;

import com.twilight.components.database.Item;

import org.jspecify.annotations.NonNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepo extends JpaRepository<@NonNull Item,@NonNull String> {
    Page<@NonNull Item> findAllByOrderId(String orderId, @NonNull Pageable pageable);
}
