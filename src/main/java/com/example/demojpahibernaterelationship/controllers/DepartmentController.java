package com.example.demojpahibernaterelationship.controllers;

import com.example.demojpahibernaterelationship.entities.Department;
import com.example.demojpahibernaterelationship.services.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {

        Department department1 = this.departmentService.saveDepartment(department);

        return ResponseEntity.ok(department1);

    }

    @PutMapping("")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {

        Department department1 = this.departmentService.update(department);

        return ResponseEntity.ok(department1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Integer id) {

        this.departmentService.delete(id);

        return ResponseEntity.ok("Delete ok");

    }


    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOne(@PathVariable("id") Integer id) {
        Department department = this.departmentService.getOneDep(id);

        Map<String , Object> response = new HashMap<>();

        response.put("data", department);
        response.put("message", "Ok good!");

        System.out.println(department);

        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAll() {

        List<Department> departments = this.departmentService.findAllDep();

        Map<String , Object> response = new HashMap<>();

        response.put("data", departments);
        response.put("message", "Ok good!");

        return ResponseEntity.ok(response);
    }


    @PostMapping("/batch")
    public ResponseEntity<Map<String, Object>> saveBatch(@RequestBody List<Department> departments) {

        List<Department> savedDepartment = this.departmentService.saveBatchDep(departments);

        Map<String , Object> response = new HashMap<>();

        response.put("data_saved", savedDepartment);
        response.put("message", "Ok good!");

        return ResponseEntity.ok(response);
    }



}
