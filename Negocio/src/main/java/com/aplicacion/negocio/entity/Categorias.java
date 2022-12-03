package com.aplicacion.negocio.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
public class Categorias implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id_Categoria;
    private String Nombre;
    private Long Categoria_Madre_Id;
    private String Nombre_Madre;

    public Categorias(Long id_Categoria, String nombre, Long categoria_Madre_Id) {
        this.Id_Categoria = id_Categoria;
        this.Nombre = nombre;
        this.Categoria_Madre_Id = categoria_Madre_Id;
    }

    public Categorias(Long id_Categoria, String nombre, String nombre_Madre) {
        Id_Categoria = id_Categoria;
        Nombre = nombre;
        Nombre_Madre = nombre_Madre;
    }

    public Categorias() {
    }

    public Categorias(Long id, String n, BigDecimal m) {
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

    public String getNombre_Madre() {
        return Nombre_Madre;
    }

    public void setNombre_Madre(String nombre_Madre) {
        Nombre_Madre = nombre_Madre;
    }

}
