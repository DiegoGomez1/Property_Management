package com.example.Property.Management.entity;

import com.example.Property.Management.enums.BuildingStatus;
import com.example.Property.Management.enums.BuildingType;
import com.example.Property.Management.enums.UnitStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buildings")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    private String zipcode;

    // Owner relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    // Building details
    @Enumerated(EnumType.STRING)
    private BuildingType buildingType = BuildingType.RESIDENTIAL;

    private Integer totalFloors;
    private Integer totalUnits;
    private Integer yearBuilt;

    @Column(length = 1000)
    private String description;

    @Column(length = 500)
    private String amenities;

    @Column(length = 300)
    private String parkingInfo;

    @Column(length = 300)
    private String petPolicy;

    // Financial information
    @Column(precision = 10, scale = 2)
    private BigDecimal monthlyMaintenanceFee;

    @Column(precision = 12, scale = 2)
    private BigDecimal propertyValue;

    @Column(precision = 12, scale = 2)
    private BigDecimal mortgageAmount;

    // Status
    @Enumerated(EnumType.STRING)
    private BuildingStatus status = BuildingStatus.ACTIVE;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Relationships
    @JsonManagedReference("building-units")
    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Unit> units = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<Expense> expenses = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<MaintenanceRequest> maintenanceRequests = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<BuildingMetrics> monthlyMetrics = new ArrayList<>();

    // Business methods
    public int getUnitCount() {
        return units != null ? units.size() : 0;
    }

    public long getOccupiedUnitsCount() {
        if (units == null) return 0;
        return units.stream()
                .filter(unit -> unit.getStatus() == UnitStatus.OCCUPIED)
                .count();
    }

    public double getOccupancyRate() {
        if (units == null || units.isEmpty()) return 0.0;
        return (double) getOccupiedUnitsCount() / units.size() * 100;
    }
}