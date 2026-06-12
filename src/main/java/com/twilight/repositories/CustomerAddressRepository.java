package com.twilight.repositories;

import com.twilight.objects.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress,Integer> {
}
