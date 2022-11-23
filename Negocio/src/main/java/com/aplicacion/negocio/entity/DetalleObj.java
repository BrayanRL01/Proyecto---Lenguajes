/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.entity;

/**
 *
 * @author XPC
 */
public class DetalleObj{
    
    private String sql_type;
    
    private Long productoID;
    private Long cantidad;
    private Float precio;
    private Float IVA;
    
    //String sql_type, Long productoID, Long cantidad, Float precio, Float IVA
    public DetalleObj(String sql_type, Long productoID, Long cantidad, Float precio, Float IVA) {
        this.sql_type = sql_type;
        this.productoID = productoID;
        this.cantidad = cantidad;
        this.precio = precio;
        this.IVA = IVA;
    }
    
    public String getSql_type() {
        return sql_type;
    }

    public void setSql_type(String sql_type) {
        this.sql_type = sql_type;
    }

    public Long getProductoID() {
        return productoID;
    }

    public void setProductoID(Long productoID) {
        this.productoID = productoID;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Float getIVA() {
        return IVA;
    }

    public void setIVA(Float IVA) {
        this.IVA = IVA;
    }

}
