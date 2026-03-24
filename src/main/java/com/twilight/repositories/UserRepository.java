package com.twilight.repositories;

import com.twilight.objects.User;
import org.jspecify.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<@NonNull User,@NonNull String> {
}
