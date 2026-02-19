package com.twilight.repositories;

import com.twilight.objects.database.Outlet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutletRepo extends JpaRepository<Outlet,String> {
}
