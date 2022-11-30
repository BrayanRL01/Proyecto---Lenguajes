package com.aplicacion.negocio.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_TIPOS_VENTA", schema = "NEGOCIO")
public class TiposVenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Venta_Id;
    private String Nombre;

    public TiposVenta(Long venta_Id, String nombre) {
        Venta_Id = venta_Id;
        Nombre = nombre;
    }

    public TiposVenta() {
    }

    public Long getVenta_Id() {
        return Venta_Id;
    }

    public void setVenta_Id(Long venta_Id) {
        Venta_Id = venta_Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

}
