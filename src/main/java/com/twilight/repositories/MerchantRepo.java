package com.twilight.repositories;

import com.twilight.components.database.Merchant;
import com.twilight.dataTransferObjects.response.component.MerchantResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepo extends JpaRepository<@NonNull Merchant, @NonNull String> {
    @Query("""
        SELECT new com.twilight.dataTransferObjects.response.component.MerchantResponse(
            c, m
        )
        FROM Merchant m
        JOIN m.customer c
    """)
    List<MerchantResponse> findAllMerchants(Pageable pageable);

    @Query("""
        SELECT new com.twilight.dataTransferObjects.response.component.MerchantResponse(
            c, m
        )
        FROM Merchant m
        JOIN m.customer c
        WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
    """)
    List<MerchantResponse> findAllMerchantsByName(@Param("name") String name, Pageable pageable);

    @Query("""
        SELECT new com.twilight.dataTransferObjects.response.component.MerchantResponse(
            c, m
        )
        FROM Merchant m
        JOIN m.customer c
        WHERE c.id =: id
    """)
    MerchantResponse findMerchantById(@NonNull @Param("id") String id);

}
