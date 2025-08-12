package com.example.Property.Management.repository;

import com.example.Property.Management.entity.Payment;
import com.example.Property.Management.enums.PaymentStatus;
import com.example.Property.Management.enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p WHERE p.lease.unit.building.id = :buildingId " +
            "AND p.paymentDate BETWEEN :startDate AND :endDate " +
            "AND p.status = :status")
    List<Payment> findByBuildingAndDateRangeAndStatus(
            @Param("buildingId") Long buildingId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("status") PaymentStatus status);

    @Query("SELECT MONTH(p.paymentDate) as month, SUM(p.amount) as revenue " +
            "FROM Payment p WHERE p.lease.unit.building.id = :buildingId " +
            "AND YEAR(p.paymentDate) = :year AND p.status = 'PAID' AND p.type = :type " +
            "GROUP BY MONTH(p.paymentDate) ORDER BY month")
    List<Object[]> getMonthlyRevenue(@Param("buildingId") Long buildingId,
                                     @Param("year") Integer year,
                                     @Param("type") PaymentType type);

    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.lease.unit.building.id = :buildingId " +
            "AND p.paymentDate BETWEEN :startDate AND :endDate AND p.status = 'PAID'")
    BigDecimal getTotalRevenueByBuildingAndDateRange(
            @Param("buildingId") Long buildingId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}