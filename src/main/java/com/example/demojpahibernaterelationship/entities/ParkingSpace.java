package com.example.demojpahibernaterelationship.entities;

import javax.persistence.*;

@Entity
public class ParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int lot;

    private String location;

//    @OneToOne(mappedBy = "parkingSpace")
//    private Employee employee;

    public ParkingSpace() {
    }

    public ParkingSpace(int lot, String location) {
        this.lot = lot;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    @Override
    public String toString() {
        return "ParkingSpace{" +
                "id=" + id +
                ", lot=" + lot +
                ", location='" + location + '\'' +
                '}';
    }
}
