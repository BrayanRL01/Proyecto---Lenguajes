/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author XPC
 */
@Entity
public class DetalleVista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IDDetalle;
    
    private Long FacturaID;
    private String producto;
    private String tamano;
    private Long precio;
    private Long cantidad;

    public Long getIDDetalle() {
        return IDDetalle;
    }

    public void setIDDetalle(Long IDDetalle) {
        this.IDDetalle = IDDetalle;
    }

    public Long getFacturaID() {
        return FacturaID;
    }

    public void setFacturaID(Long FacturaID) {
        this.FacturaID = FacturaID;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public DetalleVista(Long IDDetalle, Long FacturaID, String producto, String tamano, Long precio, Long cantidad) {
        this.IDDetalle = IDDetalle;
        this.FacturaID = FacturaID;
        this.producto = producto;
        this.tamano = tamano;
        this.precio = precio;
        this.cantidad = cantidad;
    }
}
