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
public class FacturaVista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_factura;
    
    private String vendedor;
    private String cliente;
    private String tipoVenta;
    private Long totalEntrega;
    private Long total;
    private String medioPagoID;
    private String fechaHoraVenta;

    public Long getId_factura() {
        return id_factura;
    }

    public void setId_factura(Long id_factura) {
        this.id_factura = id_factura;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
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

    public String getMedioPagoID() {
        return medioPagoID;
    }

    public void setMedioPagoID(String medioPagoID) {
        this.medioPagoID = medioPagoID;
    }

    public String getFechaHoraVenta() {
        return fechaHoraVenta;
    }

    public void setFechaHoraVenta(String fechaHoraVenta) {
        this.fechaHoraVenta = fechaHoraVenta;
    }

    public FacturaVista(Long id_factura, String vendedor, String cliente, String tipoVenta, Long totalEntrega, Long total, String medioPagoID, String fechaHoraVenta) {
        this.id_factura = id_factura;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.tipoVenta = tipoVenta;
        this.totalEntrega = totalEntrega;
        this.total = total;
        this.medioPagoID = medioPagoID;
        this.fechaHoraVenta = fechaHoraVenta;
    }
    
    
}
