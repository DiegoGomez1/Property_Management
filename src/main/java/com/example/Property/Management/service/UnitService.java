package com.example.Property.Management.service;

import com.example.Property.Management.entity.Unit;
import com.example.Property.Management.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UnitService {
    @Autowired
    private UnitRepository unitRepository;

    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    public Optional<Unit> getUnitById(Long id) {
        return unitRepository.findById(id);
    }

    public Unit saveUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    public void deleteUnit(Long id) {
        unitRepository.deleteById(id);
    }
}

