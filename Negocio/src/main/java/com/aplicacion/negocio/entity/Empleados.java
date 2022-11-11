package com.aplicacion.negocio.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEES")
public class Empleados implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Employee_Id;
    private String First_Name;
    private String Last_Name;

    public Long getEmployee_Id() {
        return Employee_Id;
    }

    public void setEmployee_Id(Long employee_Id) {
        Employee_Id = employee_Id;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

}
