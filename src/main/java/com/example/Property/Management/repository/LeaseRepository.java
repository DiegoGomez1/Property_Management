package com.example.Property.Management.repository;

import com.example.Property.Management.entity.Lease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaseRepository extends JpaRepository<Lease, Long> {
}

