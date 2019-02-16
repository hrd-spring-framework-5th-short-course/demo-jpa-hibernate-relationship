package com.example.demojpahibernaterelationship;

import com.example.demojpahibernaterelationship.entities.Employee;
import com.example.demojpahibernaterelationship.entities.QDepartment;
import com.example.demojpahibernaterelationship.entities.QEmployee;
import com.example.demojpahibernaterelationship.entities.wrappers.EmployeeWrapper;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

/*@OneToMany
@ManyToOne

@ManyToMany
@ManyToMany(mappedBy = "")


// Many to many extra column (ឈឺសាច់)

@OneToOne
@OneToOne(mappedBy = "")*/

@SpringBootApplication
@Transactional
public class DemoJpaHibernateRelationshipApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoJpaHibernateRelationshipApplication.class, args);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {

//        Department department1 = new Department("IT HRD");
//        Department department2 = new Department("HR");
////
//        this.entityManager.persist(department1);
//        this.entityManager.persist(department2);
//
//        ParkingSpace parkingSpace1 = new ParkingSpace(101, "aaa");
//        ParkingSpace parkingSpace2 = new ParkingSpace(102, "bbb");
//        ParkingSpace parkingSpace3 = new ParkingSpace(103, "ccc");
//        ParkingSpace parkingSpace4 = new ParkingSpace(104, "ccc");
//
//        this.entityManager.persist(parkingSpace1);
//        this.entityManager.persist(parkingSpace2);
//        this.entityManager.persist(parkingSpace3);
//        this.entityManager.persist(parkingSpace4);
//
//        Employee employee1 = new Employee("Dara", 450.5, department1, parkingSpace1);
//        Employee employee2 = new Employee("Bopha", 550.5, department2, parkingSpace2);
//        Employee employee3 = new Employee("Daro", 750.5, department2, parkingSpace3);
//        Employee employee4 = new Employee("Ty Ty", 1000.5, department1, parkingSpace4);
////
//        this.entityManager.persist(employee1);
//        this.entityManager.persist(employee2);
//        this.entityManager.persist(employee3);
//        this.entityManager.persist(employee4);
//
//        Department department = this.entityManager.find(Department.class, 1);
//        System.out.println(department);
//
//        Employee employee = this.entityManager.find(Employee.class, 3);
//
//        System.out.println(employee);

//        CriteriaDelete
//        CriteriaQuery
//        CriteriaUpdate

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Employee> criteriaQuery = cb.createQuery(Employee.class);

        // from employee
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.select(employeeRoot); // select * from employee
//        criteriaQuery.select(employeeRoot.get("name"));
        // conditions
        criteriaQuery.where(cb.equal(employeeRoot.get("name"), "Dara"));
//        criteriaQuery.where(cb.equal(employeeRoot.get("id"), 1));
//        List<Employee> employees = entityManager.createQuery(criteriaQuery).getResultList();
        List<Employee> employees = entityManager.createQuery(criteriaQuery).getResultList();
        System.out.println(employees);


        /****************************************************************************************************/
        CriteriaQuery<String> criteriaQueryName = cb.createQuery(String.class);
        Root<Employee> employeeRootName = criteriaQueryName.from(Employee.class);
        criteriaQueryName.select(employeeRootName.get("name")); // select name from employee
        List<String> names = entityManager.createQuery(criteriaQueryName).getResultList();

        System.out.println(names);

        /**Wrapper**************************************************************************************************/

        CriteriaQuery<EmployeeWrapper> employeeWrapperCriteriaQuery
                = cb.createQuery(EmployeeWrapper.class);

        Root<Employee> employeeRootWrapper =
                employeeWrapperCriteriaQuery.from(Employee.class);

        employeeWrapperCriteriaQuery.select(
                cb.construct(
                        EmployeeWrapper.class,
                        employeeRootWrapper.get("name"),
                        employeeRootWrapper.get("salary")
                )
        );


        List<EmployeeWrapper> employeeWrappers
                = entityManager.createQuery(employeeWrapperCriteriaQuery)
                .getResultList();


        for (EmployeeWrapper ew :
                employeeWrappers) {
            System.out.println(ew);
        }

//        employeeWrappers.forEach(System.out::println);


        /**Tuple**************************************************************************************************/

        CriteriaQuery<Tuple> tupleCriteriaQuery = cb.createTupleQuery();

        Root<Employee> tupleRootEmployee =
                tupleCriteriaQuery.from(Employee.class);

        Path<Integer> idPath = tupleRootEmployee.get("id");
        Path<String> namePath = tupleRootEmployee.get("name");

        tupleCriteriaQuery.multiselect(
                idPath,
                namePath
        );

        List<Tuple> tuples = entityManager.createQuery(tupleCriteriaQuery)
                .getResultList();

        System.out.println("Tuples****************");

        for (Tuple t :
                tuples) {
            System.out.println(t.get(idPath));
            System.out.println(t.get(namePath));
        }


        System.out.println("end criteria query!!\n\n\n");

        System.out.println("QUERY_DSL@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");


        // from clause
        QEmployee qEmployee = QEmployee.employee;
        QDepartment qDepartment = QDepartment.department;

        JPAQuery<Double> employeeJPAQuery = new JPAQuery<>(entityManager);

        List<Double> salary = employeeJPAQuery
                .select(qEmployee.salary)
                .orderBy(qEmployee.salary.desc())
                .where(qEmployee.name.like("%L%"))
                .from(qEmployee).fetch()
                ;

//        "select * from id DESC";

        System.out.println("Employee form dsl query: " + salary);


        System.out.println("select employee by specific department");
        JPAQuery<Employee> employeeJPAQuery1 = new JPAQuery<>(entityManager);

        List<Employee> employeesByDep =
                employeeJPAQuery1
                        .select(qEmployee)
                        .where(qEmployee.department.id.eq(2))
                        .from(qEmployee)
                        .fetch();


        // select count salary group by department
        JPAQuery<Double> doubleJPAQuery = new JPAQuery<>(entityManager);

        List<Double> doubles = doubleJPAQuery

                .select(qEmployee.salary.sum())
                .where(qEmployee.department.id.eq(1))
                .from(qEmployee)
                .groupBy(qEmployee.department.id)

                .fetch();

        System.out.println(doubles);

    }
}

