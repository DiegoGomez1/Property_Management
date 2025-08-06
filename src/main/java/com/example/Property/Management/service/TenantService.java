package com.example.Property.Management.service;

import com.example.Property.Management.entity.Tenant;
import com.example.Property.Management.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TenantService {
    @Autowired
    private TenantRepository tenantRepository;

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Optional<Tenant> getTenantById(Long id) {
        return tenantRepository.findById(id);
    }

    public Tenant saveTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    public void deleteTenant(Long id) {
        tenantRepository.deleteById(id);
    }
}

