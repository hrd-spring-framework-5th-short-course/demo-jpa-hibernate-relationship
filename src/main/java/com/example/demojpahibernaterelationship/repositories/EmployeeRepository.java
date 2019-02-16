package com.example.demojpahibernaterelationship.repositories;

import com.example.demojpahibernaterelationship.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    //derived query/named query
    List<Employee> getAllBySalaryGreaterThanAndNameContaining(Double salary, String nameContaining);




}