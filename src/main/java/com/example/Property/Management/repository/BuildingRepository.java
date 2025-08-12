package com.example.Property.Management.repository;

import com.example.Property.Management.entity.Building;
import com.example.Property.Management.enums.BuildingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    List<Building> findByOwnerIdAndStatus(Long ownerId, BuildingStatus status);

    List<Building> findByCityIgnoreCase(String city);

    List<Building> findByStateIgnoreCase(String state);

    @Query("SELECT b FROM Building b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Building> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT b FROM Building b LEFT JOIN FETCH b.units WHERE b.id = :id")
    Optional<Building> findByIdWithUnits(@Param("id") Long id);

    @Query("SELECT b FROM Building b LEFT JOIN FETCH b.units u LEFT JOIN FETCH u.leases l WHERE b.id = :id")
    Optional<Building> findByIdWithUnitsAndLeases(@Param("id") Long id);

    boolean existsByNameAndAddress(String name, String address);

    @Query("SELECT COUNT(b) FROM Building b WHERE b.owner.id = :ownerId")
    Long countBuildingsByOwnerId(@Param("ownerId") Long ownerId);
}