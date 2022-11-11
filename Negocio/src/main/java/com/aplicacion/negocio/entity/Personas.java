package com.aplicacion.negocio.entity;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "TAB_PERSONAS", schema = "NEGOCIO")

public class Personas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id_Persona;
    private int Cedula;
    private String Nombre;
    private String Primer_Apellido;
    private String Segundo_Apellido;
    private String Direccion;
    private String Email;
    private String Telefono;

    

    public Long getId_Persona() {
        return Id_Persona;
    }

    public void setId_Persona(Long id_Persona) {
        Id_Persona = id_Persona;
    }

    public int getCedula() {
        return Cedula;
    }

    public void setCedula(int cedula) {
        Cedula = cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPrimer_Apellido() {
        return Primer_Apellido;
    }

    public void setPrimer_Apellido(String primer_Apellido) {
        Primer_Apellido = primer_Apellido;
    }

    public String getSegundo_Apellido() {
        return Segundo_Apellido;
    }

    public void setSegundo_Apellido(String segundo_Apellido) {
        Segundo_Apellido = segundo_Apellido;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
 

}
