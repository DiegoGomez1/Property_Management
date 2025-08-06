package com.example.Property.Management.repository;

import com.example.Property.Management.entity.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building, Long> {
}

