package com.example.Property.Management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int floor;
    private String status;
    private double squareFeet;
    private int bedrooms;
    private int bathrooms;
    private String number;

    // Many units belong to one building
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    @JsonIgnore  // Prevent circular reference
    private Building building;

    // One unit can have one lease
    @OneToOne(mappedBy = "unit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore  // Prevent circular reference
    private Lease lease;

    // One unit can have many tenants
    @OneToMany(mappedBy = "unit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore  // Prevent circular reference
    private List<Tenant> tenants;
}
