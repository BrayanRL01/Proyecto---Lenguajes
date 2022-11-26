package com.aplicacion.negocio.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_DETALLES_FACTURAS", schema = "NEGOCIO")

public class Detalles_Facturas implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_DETALLE;
    private String PRODUCTO;
    private int CANTIDAD;
    private double PRECIO;
    private double TOTAL_SIN_IVA;
    private double SUBTOTAL;

    @ManyToOne
    private Facturas factura;
    
    @ManyToOne
    private Marcas Id_Marca;

    public Detalles_Facturas() {
    }
    
    public Detalles_Facturas(Long ID_DETALLE, String PRODUCTO, int CANTIDAD, double PRECIO, double TOTAL_SIN_IVA, double IVA, double SUBTOTAL) {
        this.ID_DETALLE = ID_DETALLE;
        this.PRODUCTO = PRODUCTO;
        this.CANTIDAD = CANTIDAD;
        this.PRECIO = PRECIO;
        this.TOTAL_SIN_IVA = TOTAL_SIN_IVA;
        this.SUBTOTAL = SUBTOTAL;
    }

    public Long getID_DETALLE() {
        return ID_DETALLE;
    }

    public void setID_DETALLE(Long ID_DETALLE) {
        this.ID_DETALLE = ID_DETALLE;
    }

    public String getPRODUCTO() {
        return PRODUCTO;
    }

    public void setPRODUCTO(String PRODUCTO) {
        this.PRODUCTO = PRODUCTO;
    }

    public int getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(int CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public double getPRECIO() {
        return PRECIO;
    }

    public void setPRECIO(double PRECIO) {
        this.PRECIO = PRECIO;
    }

    public double getTOTAL_SIN_IVA() {
        return TOTAL_SIN_IVA;
    }

    public void setTOTAL_SIN_IVA(double TOTAL_SIN_IVA) {
        this.TOTAL_SIN_IVA = TOTAL_SIN_IVA;
    }

    /*public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }*/

    public double getSUBTOTAL() {
        return SUBTOTAL;
    }

    public void setSUBTOTAL(double SUBTOTAL) {
        this.SUBTOTAL = SUBTOTAL;
    }

    public Facturas getFactura() {
        return factura;
    }

    public void setFactura(Facturas factura) {
        this.factura = factura;
    }

    public Marcas getId_Marca() {
        return Id_Marca;
    }

    public void setId_Marca(Marcas Id_Marca) {
        this.Id_Marca = Id_Marca;
    }
    
    
}
