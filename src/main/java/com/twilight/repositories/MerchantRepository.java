package com.twilight.repositories;

import com.twilight.objects.Merchant;
import com.twilight.dataTransferObjects.component.MerchantResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<@NonNull Merchant, @NonNull String> {
}
