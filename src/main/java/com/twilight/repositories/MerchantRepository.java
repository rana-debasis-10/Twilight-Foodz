package com.twilight.repositories;

import com.twilight.objects.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<@NonNull Merchant, @NonNull String> {
}
