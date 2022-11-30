package com.aplicacion.negocio.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "TAB_MEDIOS_PAGO", schema = "NEGOCIO")
public class MediosPago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Medio_Id;
    private String Nombre;

    public MediosPago(Long medio_Id, String nombre) {
        Medio_Id = medio_Id;
        Nombre = nombre;
    }

    public MediosPago() {
    }

    public Long getMedio_Id() {
        return Medio_Id;
    }

    public void setMedio_Id(Long medio_Id) {
        Medio_Id = medio_Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

}
