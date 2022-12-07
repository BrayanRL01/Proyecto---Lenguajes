/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.entity;

import java.math.BigDecimal;
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
    private Long productID;
    private Long FacturaID;
    private String producto;
    private Long cantidad;
    private BigDecimal precio;
    private BigDecimal totalSinIva;
    private BigDecimal IVA;
    private BigDecimal subtotal;
    private String tamano;

    public Detalles_Factura() {
       this.cantidad = (long) 0;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
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

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getTotalSinIva() {
        return totalSinIva;
    }

    public void setTotalSinIva(BigDecimal totalSinIva) {
        this.totalSinIva = totalSinIva;
    }

    public BigDecimal  getIVA() {
        return IVA;
    }

    public void setIVA(BigDecimal  IVA) {
        this.IVA = IVA;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public Detalles_Factura(Long IDDetalle, Long FacturaID, String producto, Long cantidad, BigDecimal precio, BigDecimal totalSinIva, BigDecimal  IVA, BigDecimal subtotal, String tamano) {
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
