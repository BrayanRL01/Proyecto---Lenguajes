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
    private Long Id_Categoria;
    private Long Id_Marca;
    private String Nombre;
    private String Detalle;
    private Long Precio;
    private String Tamano;
    private Long Cantidad;

    private String Marca;
    private String Categoria;

    public Productos(Long id_Producto, String codigo, Long id_Categoria, Long id_Marca, String nombre, String detalle,
            Long precio, String tamano,
            Long cantidad) {
        this.Id_Producto = id_Producto;
        this.Codigo = codigo;
        this.Id_Categoria = id_Categoria;
        this.Id_Marca = id_Marca;
        this.Nombre = nombre;
        this.Detalle = detalle;
        this.Precio = precio;
        this.Tamano = tamano;
        this.Cantidad = cantidad;
    }

    public Productos() {

    }

    public Productos(Long id_Producto, String codigo, String marca, String categoria, String nombre, String detalle,
            Long precio, String tamano,
            Long cantidad) {
        Id_Producto = id_Producto;
        Codigo = codigo;
        Marca = marca;
        Categoria = categoria;
        Nombre = nombre;
        Detalle = detalle;
        Precio = precio;
        Tamano = tamano;
        Cantidad = cantidad;
    }

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

    public Long getPrecio() {
        return Precio;
    }

    public void setPrecio(Long precio) {
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

    public Long getId_Categoria() {
        return Id_Categoria;
    }

    public void setId_Categoria(Long id_Categoria) {
        Id_Categoria = id_Categoria;
    }

    public Long getId_Marca() {
        return Id_Marca;
    }

    public void setId_Marca(Long id_Marca) {
        Id_Marca = id_Marca;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

}
