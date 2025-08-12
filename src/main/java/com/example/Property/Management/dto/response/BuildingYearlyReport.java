package com.example.Property.Management.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class BuildingYearlyReport {
    private Long buildingId;
    private String buildingName;
    private int year;

    // Financial metrics
    private BigDecimal totalRevenue;
    private BigDecimal totalExpenses;
    private BigDecimal netOperatingIncome;
    private BigDecimal roi;

    // Occupancy metrics
    private Double averageOccupancyRate;
    private Integer totalVacantDays;
    private Double tenantTurnoverRate;
    private Double averageLeaseLength;

    // Operational metrics
    private BigDecimal maintenanceCosts;
    private Double avgMaintenanceResponseTime;
    private BigDecimal costPerUnit;
    private BigDecimal revenuePerSquareFoot;

    // Monthly breakdown
    private List<MonthlyRevenue> monthlyRevenues;
    private List<ExpenseByCategory> expenseBreakdown;

    // Performance indicators
    private String bestMonth;
    private String worstMonth;
    private Double latePaymentRate;
    private Integer totalMaintenanceRequests;
}