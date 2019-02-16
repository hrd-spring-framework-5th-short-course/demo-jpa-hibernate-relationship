package com.example.demojpahibernaterelationship.configurations;


import com.example.demojpahibernaterelationship.entities.Department;
import com.example.demojpahibernaterelationship.entities.Employee;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class ExposeIdConfig implements RepositoryRestConfigurer {

    // this is to expose id or something
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Department.class);
        config.exposeIdsFor(Employee.class);
        config.setBasePath("/api");
    }



}