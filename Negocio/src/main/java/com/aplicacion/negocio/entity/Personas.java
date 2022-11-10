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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author XPC
 */
@Entity
@Table(name="TAB_PERSONAS", schema="NEGOCIO")
public class Personas implements Serializable {
    //----------------primary key------------------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id_persona;
    //----------------columnas de la tabla------------------
    private Long cedula;
    private String primer_apellido;
    private String segundo_apellido;
    private String direccion;
    private String email;
    private String telefono;
    //----------------Foreing key------------------
    @ManyToOne
    @JoinColumn(name = "tipo_persona_id")
    private Tipo_Personas tipo_persona; 
    
    //------------------set and getters----------------------

    public Long getId_persona() {
        return id_persona;
    }

    public void setId_persona(Long id_persona) {
        this.id_persona = id_persona;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Tipo_Personas getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(Tipo_Personas tipo_persona) {
        this.tipo_persona = tipo_persona;
    }
    
}
/*
DIRECCION VARCHAR2(100),
EMAIL VARCHAR2(50),
TELEFONO VARCHAR2(15) NOT NULL,
TIPO_PERSONA_ID NUMBER,
*/