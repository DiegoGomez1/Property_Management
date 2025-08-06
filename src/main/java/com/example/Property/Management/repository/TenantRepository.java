package com.example.Property.Management.repository;

import com.example.Property.Management.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}

