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
public class Detalles_Factura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IDDetalle;
    
    private Long FacturaID;
    private String producto;
    private Long cantidad;
    private Long precio;
    private Long totalSinIva;
    private Long IVA;
    private Long subtotal;
    private String tamano;

    public Detalles_Factura() {
       this.cantidad = (long) 0;
    }

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

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Long getTotalSinIva() {
        return totalSinIva;
    }

    public void setTotalSinIva(Long totalSinIva) {
        this.totalSinIva = totalSinIva;
    }

    public Long getIVA() {
        return IVA;
    }

    public void setIVA(Long IVA) {
        this.IVA = IVA;
    }

    public Long getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Long subtotal) {
        this.subtotal = subtotal;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public Detalles_Factura(Long IDDetalle, Long FacturaID, String producto, Long cantidad, Long precio, Long totalSinIva, Long IVA, Long subtotal, String tamano) {
        this.IDDetalle = IDDetalle;
        this.FacturaID = FacturaID;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.totalSinIva = totalSinIva;
        this.IVA = IVA;
        this.subtotal = subtotal;
        this.tamano = tamano;
    }
   
}
