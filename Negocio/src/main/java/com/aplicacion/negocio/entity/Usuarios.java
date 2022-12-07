package com.aplicacion.negocio.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "TAB_USUARIOS", schema = "NEGOCIO")
public class Usuarios implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Usuario_Id;
    private String Nickname;
    private String Nombre;
    private String Primer_Apellido;
    private String Segundo_Apellido;
    private String Email;
    private String Contrasena;
    private String Telefono;
    private Long Rol_Id;
    private Long Estado_Usuario_Id;

    private String Nombre_Rol;
    private String Nombre_Estado;

    public Usuarios(Long usuario_Id, String nickname, String nombre, String primer_Apellido, String segundo_Apellido,
            String email,
            String contrasena, String telefono, Long rol_Id, Long estado_Usuario_Id) {
        this.Usuario_Id = usuario_Id;
        this.Nickname = nickname;
        this.Nombre = nombre;
        this.Primer_Apellido = primer_Apellido;
        this.Segundo_Apellido = segundo_Apellido;
        this.Email = email;
        this.Contrasena = contrasena;
        this.Telefono = telefono;
        this.Rol_Id = rol_Id;
        this.Estado_Usuario_Id = estado_Usuario_Id;
    }

    public Usuarios(Long usuario_Id, String nickname, String nombre, String primer_Apellido, String segundo_Apellido,
            String email, String telefono, String nombre_Rol, String nombre_Estado) {
        this.Usuario_Id = usuario_Id;
        this.Nickname = nickname;
        this.Nombre = nombre;
        this.Primer_Apellido = primer_Apellido;
        this.Segundo_Apellido = segundo_Apellido;
        this.Email = email;
        this.Telefono = telefono;
        this.Nombre_Rol = nombre_Rol;
        this.Nombre_Estado = nombre_Estado;
    }

    public Usuarios() {
    }

    public Long getUsuario_Id() {
        return Usuario_Id;
    }

    public void setUsuario_Id(Long usuario_Id) {
        Usuario_Id = usuario_Id;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        Contrasena = contrasena;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public Long getRol_Id() {
        return Rol_Id;
    }

    public void setRol_Id(Long rol_Id) {
        Rol_Id = rol_Id;
    }

    public Long getEstado_Usuario_Id() {
        return Estado_Usuario_Id;
    }

    public void setEstado_Usuario_Id(Long estado_Usuario_Id) {
        Estado_Usuario_Id = estado_Usuario_Id;
    }

    public String getNombre_Rol() {
        return Nombre_Rol;
    }

    public void setNombre_Rol(String nombre_Rol) {
        Nombre_Rol = nombre_Rol;
    }

    public String getNombre_Estado() {
        return Nombre_Estado;
    }

    public void setNombre_Estado(String nombre_Estado) {
        Nombre_Estado = nombre_Estado;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

}
