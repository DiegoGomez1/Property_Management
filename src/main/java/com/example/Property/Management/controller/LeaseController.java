package com.example.Property.Management.controller;

import com.example.Property.Management.entity.Lease;
import com.example.Property.Management.service.LeaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/leases")
public class LeaseController {
    @Autowired
    private LeaseService leaseService;

    @GetMapping
    public List<Lease> getAllLeases() {
        return leaseService.getAllLeases();
    }

    @GetMapping("/{id}")
    public Optional<Lease> getLeaseById(@PathVariable Long id) {
        return leaseService.getLeaseById(id);
    }

    @PostMapping
    public Lease createLease(@RequestBody Lease lease) {
        return leaseService.saveLease(lease);
    }

    @DeleteMapping("/{id}")
    public void deleteLease(@PathVariable Long id) {
        leaseService.deleteLease(id);
    }
}
