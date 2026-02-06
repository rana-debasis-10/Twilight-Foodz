package com.twilight.repositories;

import com.twilight.dataTransferObjects.response.component.CustomerOrderResponse;
import com.twilight.components.database.CustomerOrder;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrderRepo extends JpaRepository<@NonNull CustomerOrder,@NonNull Long> {
    Page<@NonNull CustomerOrder> findByCustomerId(String customerId, Pageable pageable);
    @NonNull Page< @NonNull CustomerOrder> findAll(@NonNull Pageable pageable);
}
