package com.aplicacion.negocio.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "TAB_PRODUCTOS", schema = "NEGOCIO")
public class Productos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id_Producto;
    private String Codigo;

    private String Nombre;
    private String Detalle;

    public Long getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(Long id_Producto) {
        Id_Producto = id_Producto;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDetalle() {
        return Detalle;
    }

    public void setDetalle(String detalle) {
        Detalle = detalle;
    }

}
