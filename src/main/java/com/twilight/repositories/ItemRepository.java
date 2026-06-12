package com.twilight.repositories;

import com.twilight.objects.Item;

import org.jspecify.annotations.NonNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<@NonNull Item,@NonNull Integer> {
    List<Item>findAllByOrderId(@NonNull Integer orderId);
}
