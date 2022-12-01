package com.aplicacion.negocio.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "TAB_ESTADOS_USUARIO", schema = "NEGOCIO")
public class EstadoUsuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Estado_Usuario_Id;
    private String Nombre;
    
    public EstadoUsuario(Long estado_Usuario_Id, String nombre) {
        this.Estado_Usuario_Id = estado_Usuario_Id;
        this.Nombre = nombre;
    }

    public EstadoUsuario() {
    }

    public Long getEstado_Usuario_Id() {
        return Estado_Usuario_Id;
    }

    public void setEstado_Usuario_Id(Long estado_Usuario_Id) {
        Estado_Usuario_Id = estado_Usuario_Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    

    
}
