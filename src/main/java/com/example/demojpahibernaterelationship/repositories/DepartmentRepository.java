package com.example.demojpahibernaterelationship.repositories;

import com.example.demojpahibernaterelationship.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "data")
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    @Query(value = "select d from Department d")
    List<Department> getDep();

    List<Department> getDepartmentByName(String name);

}
