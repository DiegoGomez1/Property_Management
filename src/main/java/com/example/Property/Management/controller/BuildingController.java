package com.example.Property.Management.controller;

import com.example.Property.Management.entity.Building;
import com.example.Property.Management.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/buildings")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @GetMapping
    public List<Building> getAllBuildings() {
        return buildingService.getAllBuildings();
    }

    @GetMapping("/{id}")
    public Optional<Building> getBuildingById(@PathVariable Long id) {
        return buildingService.getBuildingById(id);
    }

    @PostMapping
    public Building createBuilding(@RequestBody Building building) {
        return buildingService.saveBuilding(building);
    }

    @DeleteMapping("/{id}")
    public void deleteBuilding(@PathVariable Long id) {
        buildingService.deleteBuilding(id);
    }
}
