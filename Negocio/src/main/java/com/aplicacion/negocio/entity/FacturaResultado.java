/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.entity;

/**
 *
 * @author keylo
 */
public class FacturaResultado {
    private int resultado;
    private String Mensaje;

    public FacturaResultado(int resultado, String Mensaje) {
        this.resultado = resultado;
        this.Mensaje = Mensaje;
    }
    
    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }
    
    
    
    
}
