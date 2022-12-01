package com.aplicacion.negocio.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "TAB_ROLES", schema = "NEGOCIO")
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Rol_Id;
    private String Nombre;

    public Roles(Long rol_Id, String nombre) {
        Rol_Id = rol_Id;
        Nombre = nombre;
    }

    public Roles() {
    }

    public Long getRol_Id() {
        return Rol_Id;
    }

    public void setRol_Id(Long rol_Id) {
        Rol_Id = rol_Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    
}
