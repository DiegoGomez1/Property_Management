package com.example.Property.Management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "maintenance_requests")
public class MaintenanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private User tenant;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private MaintenanceStatus status = MaintenanceStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private MaintenancePriority priority = MaintenancePriority.MEDIUM;

    @Column(precision = 10, scale = 2)
    private BigDecimal estimatedCost;

    @Column(precision = 10, scale = 2)
    private BigDecimal actualCost;

    private LocalDateTime scheduledDate;
    private LocalDateTime completedDate;

    private String assignedTo;
    private String notes;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public boolean isCompleted() {
        return status == MaintenanceStatus.COMPLETED;
    }

    public boolean isOverdue() {
        return status != MaintenanceStatus.COMPLETED && 
               scheduledDate != null && 
               LocalDateTime.now().isAfter(scheduledDate);
    }

    // Enums for status and priority
    public enum MaintenanceStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }

    public enum MaintenancePriority {
        LOW,
        MEDIUM,
        HIGH,
        EMERGENCY
    }
}