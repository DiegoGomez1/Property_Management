package com.example.Property.Management.repository;

import com.example.Property.Management.entity.Expense;
import com.example.Property.Management.enums.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Find expenses by building
    List<Expense> findByBuildingId(Long buildingId);

    // Find expenses by building and date range
    List<Expense> findByBuildingIdAndDateBetween(Long buildingId, LocalDate startDate, LocalDate endDate);

    // Find expenses by category
    List<Expense> findByBuildingIdAndCategory(Long buildingId, ExpenseCategory category);

    // Find expenses by building, category, and date range
    List<Expense> findByBuildingIdAndCategoryAndDateBetween(
            Long buildingId,
            ExpenseCategory category,
            LocalDate startDate,
            LocalDate endDate);

    // Get total expenses for a building in a date range
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.building.id = :buildingId " +
            "AND e.date BETWEEN :startDate AND :endDate")
    BigDecimal getTotalExpensesByBuildingAndDateRange(
            @Param("buildingId") Long buildingId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    // Get monthly expenses breakdown for analytics
    @Query("SELECT MONTH(e.date) as month, SUM(e.amount) as totalExpenses " +
            "FROM Expense e WHERE e.building.id = :buildingId " +
            "AND YEAR(e.date) = :year " +
            "GROUP BY MONTH(e.date) ORDER BY month")
    List<Object[]> getMonthlyExpenses(@Param("buildingId") Long buildingId, @Param("year") Integer year);

    // Get expenses by category for a building and year
    @Query("SELECT e.category, SUM(e.amount) as totalAmount " +
            "FROM Expense e WHERE e.building.id = :buildingId " +
            "AND YEAR(e.date) = :year " +
            "GROUP BY e.category ORDER BY totalAmount DESC")
    List<Object[]> getExpensesByCategory(@Param("buildingId") Long buildingId, @Param("year") Integer year);

    // Get expenses by category and date range
    @Query("SELECT e.category, SUM(e.amount) as totalAmount " +
            "FROM Expense e WHERE e.building.id = :buildingId " +
            "AND e.date BETWEEN :startDate AND :endDate " +
            "GROUP BY e.category ORDER BY totalAmount DESC")
    List<Object[]> getExpensesByCategoryAndDateRange(
            @Param("buildingId") Long buildingId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    // Find top vendors by spending for a building
    @Query("SELECT e.vendor, SUM(e.amount) as totalSpent " +
            "FROM Expense e WHERE e.building.id = :buildingId " +
            "AND e.vendor IS NOT NULL " +
            "AND e.date BETWEEN :startDate AND :endDate " +
            "GROUP BY e.vendor ORDER BY totalSpent DESC")
    List<Object[]> getTopVendorsBySpending(
            @Param("buildingId") Long buildingId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    // Get monthly expense totals for calculating average (better approach)
    @Query("SELECT YEAR(e.date), MONTH(e.date), SUM(e.amount) " +
            "FROM Expense e WHERE e.building.id = :buildingId " +
            "AND e.date BETWEEN :startDate AND :endDate " +
            "GROUP BY YEAR(e.date), MONTH(e.date) " +
            "ORDER BY YEAR(e.date), MONTH(e.date)")
    List<Object[]> getMonthlyExpenseTotals(
            @Param("buildingId") Long buildingId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    // Find recent expenses for a building (last N records)
    @Query("SELECT e FROM Expense e WHERE e.building.id = :buildingId " +
            "ORDER BY e.date DESC, e.createdAt DESC")
    List<Expense> findRecentExpensesByBuildingId(@Param("buildingId") Long buildingId);

    // Search expenses by description
    @Query("SELECT e FROM Expense e WHERE e.building.id = :buildingId " +
            "AND LOWER(e.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Expense> searchExpensesByDescription(
            @Param("buildingId") Long buildingId,
            @Param("searchTerm") String searchTerm);

    // Get yearly expense comparison
    @Query("SELECT YEAR(e.date) as year, SUM(e.amount) as totalExpenses " +
            "FROM Expense e WHERE e.building.id = :buildingId " +
            "GROUP BY YEAR(e.date) ORDER BY year")
    List<Object[]> getYearlyExpenseComparison(@Param("buildingId") Long buildingId);

    // Find expenses above a certain amount
    List<Expense> findByBuildingIdAndAmountGreaterThanOrderByAmountDesc(Long buildingId, BigDecimal amount);

    // Count expenses by building and date range
    @Query("SELECT COUNT(e) FROM Expense e WHERE e.building.id = :buildingId " +
            "AND e.date BETWEEN :startDate AND :endDate")
    Long countExpensesByBuildingAndDateRange(
            @Param("buildingId") Long buildingId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    // Get maintenance expenses specifically
    @Query("SELECT SUM(e.amount) FROM Expense e WHERE e.building.id = :buildingId " +
            "AND e.category IN ('MAINTENANCE', 'REPAIRS') " +
            "AND e.date BETWEEN :startDate AND :endDate")
    BigDecimal getMaintenanceExpenses(
            @Param("buildingId") Long buildingId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    // Find all expenses for buildings owned by a specific owner
    @Query("SELECT e FROM Expense e WHERE e.building.owner.id = :ownerId " +
            "AND e.date BETWEEN :startDate AND :endDate " +
            "ORDER BY e.date DESC")
    List<Expense> findExpensesByOwnerAndDateRange(
            @Param("ownerId") Long ownerId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    // Check if building has any expenses
    boolean existsByBuildingId(Long buildingId);
}