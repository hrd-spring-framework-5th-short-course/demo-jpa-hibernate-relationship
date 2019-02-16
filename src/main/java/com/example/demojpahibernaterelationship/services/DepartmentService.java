package com.example.demojpahibernaterelationship.services;

import com.example.demojpahibernaterelationship.entities.Department;
import com.example.demojpahibernaterelationship.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;

//    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public Department saveDepartment(Department department){
        return this.departmentRepository.save(department);
    }


    public Department update(Department department){

        Department department1 = this.departmentRepository
                .getOne(department.getId());

        department1.setName(department.getName());

        return this.departmentRepository.save(department1);

    }

    public void delete(Integer id) {
        this.departmentRepository.deleteById(id);

    }

    public Department getOneDep(Integer id) {
        return this.departmentRepository.getOne(id);
    }

    public List<Department> findAllDep(){
        return this.departmentRepository.findAll();
    }

    public List<Department> saveBatchDep(List<Department> departments){
        return  this.departmentRepository.saveAll(departments);
    }


}
