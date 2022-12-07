package com.aplicacion.negocio.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Personas implements Serializable {
    // ----------------primary key------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_persona;
    // ----------------columnas de la tabla------------------
    private Long cedula;
    private String nombre;
    private String primerAp;
    private String segundoAp;
    private String direccion;
    private String email;
    private String telefono;
    // ----------------Foreing key------------------
    // @ManyToOne
    // @JoinColumn(name = "tipo_persona_id")
    private Long tipoPersonaId;
    private String tipoPersonaDesc;

    public Personas(Long id_persona, Long cedula, String nombre, String primerAp, String segundoAp, String direccion,
            String email, String telefono, Long tipoPersonaId) {
        this.id_persona = id_persona;
        this.cedula = cedula;
        this.nombre = nombre;
        this.primerAp = primerAp;
        this.segundoAp = segundoAp;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.tipoPersonaId = tipoPersonaId;
    }

    public Personas(Long id_persona, Long cedula, String nombre, String primerAp, String segundoAp, String direccion,
            String email, String telefono, String tipoPersonaDesc) {
        this.id_persona = id_persona;
        this.cedula = cedula;
        this.nombre = nombre;
        this.primerAp = primerAp;
        this.segundoAp = segundoAp;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.tipoPersonaDesc = tipoPersonaDesc;
    }

    public Personas() {

    }

    // ------------------set and getters----------------------

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerAp() {
        return primerAp;
    }

    public void setPrimerAp(String primerAp) {
        this.primerAp = primerAp;
    }

    public String getSegundoAp() {
        return segundoAp;
    }

    public void setSegundoAp(String segundoAp) {
        this.segundoAp = segundoAp;
    }

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

    public Long getTipoPersonaId() {
        return tipoPersonaId;
    }

    public void setTipoPersonaId(Long tipoPersonaId) {
        this.tipoPersonaId = tipoPersonaId;
    }

    public String getTipoPersonaDesc() {
        return tipoPersonaDesc;
    }

    public void setTipoPersonaDesc(String tipoPersonaDesc) {
        this.tipoPersonaDesc = tipoPersonaDesc;
    }

}
/*
 * DIRECCION VARCHAR2(100),
 * EMAIL VARCHAR2(50),
 * TELEFONO VARCHAR2(15) NOT NULL,
 * TIPO_PERSONA_ID NUMBER,
 */