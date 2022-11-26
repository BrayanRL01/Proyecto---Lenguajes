package com.aplicacion.negocio.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_FACTURAS", schema = "NEGOCIO")

public class Facturas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID_FACTURA;

    private int TOTAL;
    private Date FECHA_HORA_VENTA;
    
    @OneToMany(mappedBy = "factura")
    private List<Detalles_Facturas> detalles;

    public Facturas() {
    }

    public Facturas(long ID_FACTURA, int TOTAL, Date FECHA_HORA_VENTA) {
        this.ID_FACTURA = ID_FACTURA;
        this.TOTAL = TOTAL;
        this.FECHA_HORA_VENTA = FECHA_HORA_VENTA;
    }

    public long getID_FACTURA() {
        return ID_FACTURA;
    }

    public void setID_FACTURA(long ID_FACTURA) {
        this.ID_FACTURA = ID_FACTURA;
    }

    public int getTOTAL() {
        return TOTAL;
    }

    public void setTOTAL(int TOTAL) {
        this.TOTAL = TOTAL;
    }

    public Date getFECHA_HORA_VENTA() {
        return FECHA_HORA_VENTA;
    }

    public void setFECHA_HORA_VENTA(Date FECHA_HORA_VENTA) {
        this.FECHA_HORA_VENTA = FECHA_HORA_VENTA;
    }

    public List<Detalles_Facturas> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detalles_Facturas> detalles) {
        this.detalles = detalles;
    }
    
}

