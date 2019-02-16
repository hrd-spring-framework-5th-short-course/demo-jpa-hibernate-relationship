package com.example.demojpahibernaterelationship.repositories;

import com.example.demojpahibernaterelationship.entities.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Integer> {
}
