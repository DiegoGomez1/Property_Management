-- PostgreSQL Database Setup for Property Management System
-- Run this script to create the database and user

-- Create database
CREATE DATABASE property_management;

-- Create user (optional - you can use existing postgres user)
-- CREATE USER property_user WITH PASSWORD 'property_password';
-- GRANT ALL PRIVILEGES ON DATABASE property_management TO property_user;

-- Connect to the database
\c property_management;

-- The tables will be created automatically by Hibernate when you start the application
-- due to spring.jpa.hibernate.ddl-auto=update setting

-- Optional: Insert some sample data after first startup
/*
INSERT INTO users (first_name, last_name, email, phone, password, role, status, created_at, updated_at) 
VALUES 
    ('John', 'Doe', 'john.doe@example.com', '555-0123', 'password123', 'OWNER', 'ACTIVE', NOW(), NOW()),
    ('Jane', 'Smith', 'jane.smith@example.com', '555-0124', 'password123', 'TENANT', 'ACTIVE', NOW(), NOW());

INSERT INTO buildings (name, address, city, state, zipcode, owner_id, building_type, total_floors, total_units, year_built, status, created_at, updated_at)
VALUES 
    ('Sunset Apartments', '123 Main St', 'Los Angeles', 'CA', '90210', 1, 'RESIDENTIAL', 3, 24, 2020, 'ACTIVE', NOW(), NOW());
*/