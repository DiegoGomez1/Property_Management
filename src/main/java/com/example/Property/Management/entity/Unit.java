package com.example.Property.Management.entity;

import com.example.Property.Management.enums.LeaseStatus;
import com.example.Property.Management.enums.UnitStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String unitNumber;

    private Integer bedrooms;
    private Integer bathrooms;
    private Double squareFeet;

    @Column(precision = 10, scale = 2)
    private BigDecimal rentAmount;

    @Column(precision = 10, scale = 2)
    private BigDecimal securityDepositAmount;

    @Enumerated(EnumType.STRING)
    private UnitStatus status = UnitStatus.VACANT;

    @JsonBackReference("building-units")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @JsonManagedReference("unit-leases")
    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL)
    private List<Lease> leases = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL)
    private List<MaintenanceRequest> maintenanceRequests = new ArrayList<>();

    public Lease getCurrentLease() {
        return leases.stream()
                .filter(lease -> lease.getStatus() == LeaseStatus.ACTIVE)
                .findFirst()
                .orElse(null);
    }
}