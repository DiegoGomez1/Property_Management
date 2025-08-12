package com.example.Property.Management.repository;

import com.example.Property.Management.entity.User;
import com.example.Property.Management.enums.UserRole;
import com.example.Property.Management.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Check if email exists
    boolean existsByEmail(String email);

    // Find users by role
    List<User> findByRole(UserRole role);

    // Find users by status
    List<User> findByStatus(UserStatus status);

    // Find users by role and status
    List<User> findByRoleAndStatus(UserRole role, UserStatus status);

    // Search users by name (first or last name)
    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :name, '%')) " +
           "OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> findByNameContaining(@Param("name") String name);

    // Find active property owners
    @Query("SELECT u FROM User u WHERE u.role = 'OWNER' AND u.status = 'ACTIVE'")
    List<User> findActiveOwners();

    // Find active tenants
    @Query("SELECT u FROM User u WHERE u.role = 'TENANT' AND u.status = 'ACTIVE'")
    List<User> findActiveTenants();

    // Count users by role
    long countByRole(UserRole role);

    // Find users with buildings (owners with properties)
    @Query("SELECT DISTINCT u FROM User u WHERE u.role = 'OWNER' AND SIZE(u.ownedBuildings) > 0")
    List<User> findOwnersWithProperties();

    // Find tenants with active leases
    @Query("SELECT DISTINCT u FROM User u JOIN u.leases l WHERE u.role = 'TENANT' AND l.status = 'ACTIVE'")
    List<User> findTenantsWithActiveLeases();
}