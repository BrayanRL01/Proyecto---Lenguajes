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
public class Tipo_Personas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_tipo_persona;
    private String nombre;

    public Long getId_tipo_persona() {
        return id_tipo_persona;
    }

    public void setId_tipo_persona(Long id_tipo_persona) {
        this.id_tipo_persona = id_tipo_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tipo_Personas(Long id_tipo_persona, String nombre) {
        this.id_tipo_persona = id_tipo_persona;
        this.nombre = nombre;
    }

    public Tipo_Personas() {
    }

    

}
