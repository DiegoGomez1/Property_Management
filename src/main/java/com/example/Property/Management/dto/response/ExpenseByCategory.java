package com.example.Property.Management.dto.response;

import com.example.Property.Management.enums.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseByCategory {
    private ExpenseCategory category;
    private String categoryName;
    private BigDecimal totalAmount;
    private Double percentage;
    private Integer transactionCount;

    public ExpenseByCategory(ExpenseCategory category, BigDecimal totalAmount) {
        this.category = category;
        this.categoryName = category.name();
        this.totalAmount = totalAmount;
    }

    public ExpenseByCategory(ExpenseCategory category, BigDecimal totalAmount, Integer transactionCount) {
        this.category = category;
        this.categoryName = category.name();
        this.totalAmount = totalAmount;
        this.transactionCount = transactionCount;
    }
}