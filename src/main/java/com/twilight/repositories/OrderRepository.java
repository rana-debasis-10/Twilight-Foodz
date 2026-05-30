package com.twilight.repositories;

import com.twilight.objects.Order;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface OrderRepository extends JpaRepository<@NonNull Order,@NonNull Long> {
    List<Order> findByCustomerMobNo(String mobNo, Pageable pageable);
}
