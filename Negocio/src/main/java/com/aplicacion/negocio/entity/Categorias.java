package com.aplicacion.negocio.entity;

import java.io.Serializable;

import javax.persistence.*;
@Entity
public class Categorias implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id_Categoria;
    private String Nombre;
    private Long Categoria_Madre_Id;

    public Categorias(Long id_Categoria, String nombre, Long categoria_Madre_Id) {
        Id_Categoria = id_Categoria;
        Nombre = nombre;
        Categoria_Madre_Id = categoria_Madre_Id;
    }

    public Long getId_Categoria() {
        return Id_Categoria;
    }

    public void setId_Categoria(Long id_Categoria) {
        Id_Categoria = id_Categoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Long getCategoria_Madre_Id() {
        return Categoria_Madre_Id;
    }

    public void setCategoria_Madre_Id(Long categoria_Madre_Id) {
        Categoria_Madre_Id = categoria_Madre_Id;
    }

}
