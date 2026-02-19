package com.twilight.repositories;

import com.twilight.objects.database.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepo extends JpaRepository<Driver,String> {
}
