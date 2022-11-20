package com.aplicacion.negocio.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "TAB_CATEGORIAS", schema = "NEGOCIO")
public class Productos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id_Categoria;
    private String Codigo;
    private String Nombre;
    private String Detalle;
    private long Precio;
    private String Tamano;
    private Long Cantidad;

    public Productos(Long id_Categoria, String codigo, String nombre, String detalle, long precio, String tamano,
            Long cantidad) {
        Id_Categoria = id_Categoria;
        Codigo = codigo;
        Nombre = nombre;
        Detalle = detalle;
        Precio = precio;
        Tamano = tamano;
        Cantidad = cantidad;
    }

    public Long getId_Categoria() {
        return Id_Categoria;
    }

    public void setId_Categoria(Long id_Categoria) {
        Id_Categoria = id_Categoria;
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

    public long getPrecio() {
        return Precio;
    }

    public void setPrecio(long precio) {
        Precio = precio;
    }

    public String getTamano() {
        return Tamano;
    }

    public void setTamano(String tamano) {
        Tamano = tamano;
    }

    public Long getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Long cantidad) {
        Cantidad = cantidad;
    }

}
