package com.example.Property.Management.dto;

import java.time.LocalDate;
import java.util.List;

public class LeaseDTO {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double rentPrice;
    private List<TenantDTO> tenants;
    // No unit reference for simplicity

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public double getRentPrice() { return rentPrice; }
    public void setRentPrice(double rentPrice) { this.rentPrice = rentPrice; }
    public List<TenantDTO> getTenants() { return tenants; }
    public void setTenants(List<TenantDTO> tenants) { this.tenants = tenants; }
}

