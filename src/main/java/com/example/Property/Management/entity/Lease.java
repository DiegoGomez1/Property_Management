package com.example.Property.Management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leases")
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private double rentPrice;

    // Lease can include multiple tenants
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "lease_tenants",
            joinColumns = @JoinColumn(name = "lease_id"),
            inverseJoinColumns = @JoinColumn(name = "tenant_id")
    )
    private List<Tenant> tenants;

    // Lease belongs to a unit
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    @JsonIgnore  // Prevents circular reference
    private Unit unit;
}
