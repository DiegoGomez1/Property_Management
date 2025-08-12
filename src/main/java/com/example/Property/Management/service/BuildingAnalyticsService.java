package com.example.Property.Management.service;

import com.example.Property.Management.dto.response.BuildingYearlyReport;
import com.example.Property.Management.dto.response.MonthlyRevenue;
import com.example.Property.Management.entity.Building;
import com.example.Property.Management.enums.PaymentType;
import com.example.Property.Management.enums.UnitStatus;
import com.example.Property.Management.exception.BuildingNotFoundException;
import com.example.Property.Management.repository.BuildingRepository;
import com.example.Property.Management.repository.ExpenseRepository;
import com.example.Property.Management.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BuildingAnalyticsService {

    private final BuildingRepository buildingRepository;
    private final PaymentRepository paymentRepository;
    private final ExpenseRepository expenseRepository;

    public BuildingYearlyReport getYearlyPerformance(Long buildingId, int year) {
        Building building = buildingRepository.findByIdWithUnitsAndLeases(buildingId)
                .orElseThrow(() -> new BuildingNotFoundException("Building not found with id: " + buildingId));

        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = LocalDate.of(year, 12, 31);

        // Calculate financial metrics
        BigDecimal totalRevenue = paymentRepository.getTotalRevenueByBuildingAndDateRange(
                buildingId, startOfYear, endOfYear);

        BigDecimal totalExpenses = expenseRepository.getTotalExpensesByBuildingAndDateRange(
                buildingId, startOfYear, endOfYear);

        BigDecimal netOperatingIncome = totalRevenue.subtract(totalExpenses);

        // Calculate ROI if property value is available
        BigDecimal roi = BigDecimal.ZERO;
        if (building.getPropertyValue() != null && building.getPropertyValue().compareTo(BigDecimal.ZERO) > 0) {
            roi = netOperatingIncome.divide(building.getPropertyValue(), 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
        }

        // Calculate occupancy metrics
        double averageOccupancyRate = calculateAverageOccupancy(building, year);

        // Get monthly breakdown
        List<MonthlyRevenue> monthlyRevenues = getMonthlyBreakdown(buildingId, year);

        return BuildingYearlyReport.builder()
                .buildingId(buildingId)
                .buildingName(building.getName())
                .year(year)
                .totalRevenue(totalRevenue)
                .totalExpenses(totalExpenses)
                .netOperatingIncome(netOperatingIncome)
                .roi(roi)
                .averageOccupancyRate(averageOccupancyRate)
                .monthlyRevenues(monthlyRevenues)
                .build();
    }

    private double calculateAverageOccupancy(Building building, int year) {
        if (building.getUnits() == null || building.getUnits().isEmpty()) {
            return 0.0;
        }

        // This is a simplified calculation - you'd want more sophisticated logic
        // that considers lease start/end dates throughout the year
        long occupiedUnits = building.getUnits().stream()
                .filter(unit -> unit.getStatus() == UnitStatus.OCCUPIED)
                .count();

        return (double) occupiedUnits / building.getUnits().size() * 100;
    }

    private List<MonthlyRevenue> getMonthlyBreakdown(Long buildingId, int year) {
        List<Object[]> revenueData = paymentRepository.getMonthlyRevenue(buildingId, year, PaymentType.RENT);
        List<Object[]> expenseData = expenseRepository.getMonthlyExpenses(buildingId, year);

        List<MonthlyRevenue> monthlyRevenues = new ArrayList<>();

        for (int month = 1; month <= 12; month++) {
            BigDecimal revenue = findRevenueForMonth(revenueData, month);
            BigDecimal expenses = findExpensesForMonth(expenseData, month);

            monthlyRevenues.add(new MonthlyRevenue(
                    month,
                    Month.of(month).name(),
                    revenue,
                    expenses,
                    revenue.subtract(expenses),
                    0.0 // You'd calculate monthly occupancy here
            ));
        }

        return monthlyRevenues;
    }

    private BigDecimal findRevenueForMonth(List<Object[]> data, int month) {
        return data.stream()
                .filter(row -> ((Number) row[0]).intValue() == month)
                .findFirst()
                .map(row -> (BigDecimal) row[1])
                .orElse(BigDecimal.ZERO);
    }

    private BigDecimal findExpensesForMonth(List<Object[]> data, int month) {
        return data.stream()
                .filter(row -> ((Number) row[0]).intValue() == month)
                .findFirst()
                .map(row -> (BigDecimal) row[1])
                .orElse(BigDecimal.ZERO);
    }
}