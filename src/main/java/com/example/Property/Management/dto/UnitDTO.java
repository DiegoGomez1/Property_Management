package com.example.Property.Management.dto;

public class UnitDTO {
    private Long id;
    private int floor;
    private String status;
    private double squareFeet;
    private int bedrooms;
    private int bathrooms;
    private String number;
    private String buildingName; // Only the building name
    private LeaseDTO lease;
    // No tenants list for simplicity

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getSquareFeet() { return squareFeet; }
    public void setSquareFeet(double squareFeet) { this.squareFeet = squareFeet; }
    public int getBedrooms() { return bedrooms; }
    public void setBedrooms(int bedrooms) { this.bedrooms = bedrooms; }
    public int getBathrooms() { return bathrooms; }
    public void setBathrooms(int bathrooms) { this.bathrooms = bathrooms; }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public String getBuildingName() { return buildingName; }
    public void setBuildingName(String buildingName) { this.buildingName = buildingName; }
    public LeaseDTO getLease() { return lease; }
    public void setLease(LeaseDTO lease) { this.lease = lease; }
}

