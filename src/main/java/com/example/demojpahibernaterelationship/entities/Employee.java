package com.example.demojpahibernaterelationship.entities;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(columnDefinition = "text")
    private String name;


    private Double salary;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "state", column = @Column(name = "province")),
            @AttributeOverride(name = "zip", column = @Column(name = "postal_code"))
    })
    private Address address;

    @ManyToOne(optional = false)
    @JoinColumn(name = "dep_id")
    private Department department;

    @OneToOne(optional = false)
    @JoinColumn(name = "ps_id")
    private ParkingSpace parkingSpace;

    public Employee() {
    }

    public Employee(String name, Double salary, Department department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public Employee(String name, Double salary, ParkingSpace parkingSpace) {
        this.name = name;
        this.salary = salary;
        this.parkingSpace = parkingSpace;
    }

    public Employee(String name, Double salary, Department department, ParkingSpace parkingSpace) {
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.parkingSpace = parkingSpace;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(ParkingSpace parkingSpace) {
        this.parkingSpace = parkingSpace;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                ", parkingSpace=" + parkingSpace +
                ", address=" + address +
                '}';
    }
}
