package com.aplicacion.negocio.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Marcas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id_Marca;
    private String Nombre_Marca;

    public Marcas(Long id_Marca, String nombre_Marca) {
        Id_Marca = id_Marca;
        Nombre_Marca = nombre_Marca;
    }

    public Long getId_Marca() {
        return Id_Marca;
    }

    public void setId_Marca(Long id_Marca) {
        Id_Marca = id_Marca;
    }

    public String getNombre_Marca() {
        return Nombre_Marca;
    }

    public void setNombre_Marca(String nombre_Marca) {
        Nombre_Marca = nombre_Marca;
    }

}
