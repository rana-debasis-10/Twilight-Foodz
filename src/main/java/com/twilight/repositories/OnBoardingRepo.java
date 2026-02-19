package com.twilight.repositories;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.twilight.objects.database.OnBoarding;

@Repository
public interface OnBoardingRepo extends JpaRepository<@NonNull OnBoarding, @NonNull String> {
}
