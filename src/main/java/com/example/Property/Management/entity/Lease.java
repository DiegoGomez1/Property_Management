package com.example.Property.Management.entity;

import com.example.Property.Management.enums.LeaseStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "leases")
public class Lease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference("unit-leases")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private User tenant;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal monthlyRent;

    @Column(precision = 10, scale = 2)
    private BigDecimal securityDeposit;

    @Enumerated(EnumType.STRING)
    private LeaseStatus status = LeaseStatus.ACTIVE;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "lease", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    public boolean isActive() {
        return status == LeaseStatus.ACTIVE &&
                LocalDate.now().isAfter(startDate.minusDays(1)) &&
                LocalDate.now().isBefore(endDate.plusDays(1));
    }
}