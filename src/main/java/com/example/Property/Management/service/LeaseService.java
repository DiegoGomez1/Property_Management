package com.example.Property.Management.service;

import com.example.Property.Management.entity.Lease;
import com.example.Property.Management.repository.LeaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LeaseService {
    @Autowired
    private LeaseRepository leaseRepository;

    public List<Lease> getAllLeases() {
        return leaseRepository.findAll();
    }

    public Optional<Lease> getLeaseById(Long id) {
        return leaseRepository.findById(id);
    }

    public Lease saveLease(Lease lease) {
        return leaseRepository.save(lease);
    }

    public void deleteLease(Long id) {
        leaseRepository.deleteById(id);
    }
}

