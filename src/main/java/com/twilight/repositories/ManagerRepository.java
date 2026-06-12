package com.twilight.repositories;

import com.twilight.objects.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager,String> {
}
