package com.twilight.repositories;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.twilight.components.database.OnBoardingDetails;

@Repository
public interface OnBoardingRepo extends JpaRepository<@NonNull OnBoardingDetails, @NonNull String> {
}
