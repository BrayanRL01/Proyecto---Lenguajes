/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author XPC
 */
@Entity
public class Facturas implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_factura;
    
    private Long vendedorID;
    private Long clienteID;
    private Long tipoVentaID;
    private Long totalEntrega;
    private Long total;
    private Long medioPagoID;
    private String fechaHoraVenta;

    public Long getId_factura() {
        return id_factura;
    }

    public void setId_factura(Long id_factura) {
        this.id_factura = id_factura;
    }

    public Long getVendedorID() {
        return vendedorID;
    }

    public void setVendedorID(Long vendedorID) {
        this.vendedorID = vendedorID;
    }

    public Long getClienteID() {
        return clienteID;
    }

    public void setClienteID(Long clienteID) {
        this.clienteID = clienteID;
    }

    public Long getTipoVentaID() {
        return tipoVentaID;
    }

    public void setTipoVentaID(Long tipoVentaID) {
        this.tipoVentaID = tipoVentaID;
    }

    public Long getTotalEntrega() {
        return totalEntrega;
    }

    public void setTotalEntrega(Long totalEntrega) {
        this.totalEntrega = totalEntrega;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getMedioPagoID() {
        return medioPagoID;
    }

    public void setMedioPagoID(Long medioPagoID) {
        this.medioPagoID = medioPagoID;
    }

    public String getFechaHoraVenta() {
        return fechaHoraVenta;
    }

    public void setFechaHoraVenta(String fechaHoraVenta) {
        this.fechaHoraVenta = fechaHoraVenta;
    }

    public Facturas(Long id_factura, Long vendedorID, Long clienteID, Long tipoVentaID, Long totalEntrega, Long total, Long medioPagoID, String fechaHoraVenta) {
        this.id_factura = id_factura;
        this.vendedorID = vendedorID;
        this.clienteID = clienteID;
        this.tipoVentaID = tipoVentaID;
        this.totalEntrega = totalEntrega;
        this.total = total;
        this.medioPagoID = medioPagoID;
        this.fechaHoraVenta = fechaHoraVenta;
    }
    
    
    
    
}
