package com.example.Property.Management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyRevenue {
    private Integer month;
    private String monthName;
    private BigDecimal revenue;
    private BigDecimal expenses;
    private BigDecimal netIncome;
    private Double occupancyRate;
}