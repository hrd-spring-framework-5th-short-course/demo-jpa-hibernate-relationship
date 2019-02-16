package com.example.demojpahibernaterelationship.entities.unary;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Menu {

    @Id
    private Integer id;

    private String name;

    @ManyToOne
    private Menu subMenu;


    public Menu(Integer id, String name, Menu subMenu) {
        this.id = id;
        this.name = name;
        this.subMenu = subMenu;
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

    public Menu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(Menu subMenu) {
        this.subMenu = subMenu;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subMenu=" + subMenu +
                '}';
    }
}
