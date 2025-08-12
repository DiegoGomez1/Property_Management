package com.example.Property.Management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "building_metrics", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"building_id", "month", "year"})
})
public class BuildingMetrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private Integer year;

    @Column(precision = 5, scale = 2)
    private BigDecimal occupancyRate;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalRevenue;

    @Column(precision = 10, scale = 2)
    private BigDecimal totalExpenses;

    @Column(precision = 10, scale = 2)
    private BigDecimal netOperatingIncome;

    private Integer activeLeases;
    private Integer newLeases;
    private Integer terminatedLeases;
    private Integer maintenanceRequests;

    @Column(precision = 8, scale = 2)
    private BigDecimal avgResponseTime; // in days

    public BigDecimal getNetOperatingIncome() {
        if (totalRevenue == null || totalExpenses == null) {
            return BigDecimal.ZERO;
        }
        return totalRevenue.subtract(totalExpenses);
    }
}