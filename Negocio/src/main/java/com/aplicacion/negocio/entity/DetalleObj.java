/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.entity;


import java.math.BigDecimal;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

/**
 *
 * @author XPC
 */
public class DetalleObj implements SQLData{
    
    private String sql_type; 
    private Long productoID;
    private int cantidad;
    private Long precio;
    private Long IVA;
    
    //String sql_type, Long productoID, Long cantidad, Float precio, Float IVA
    public DetalleObj(String sql_type, Long productoID, int cantidad, Long precio, Long IVA) {
        this.sql_type = sql_type;
        this.productoID = productoID;
        this.cantidad = cantidad;
        this.precio = precio;
        this.IVA = IVA;
    }

    public Long getProductoID() {
        return productoID;
    }

    public void setProductoID(Long productoID) {
        this.productoID = productoID;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }

    public Long getIVA() {
        return IVA;
    }

    public void setIVA(Long IVA) {
        this.IVA = IVA;
    }

    @Override
    public String getSQLTypeName() throws SQLException { 
    return sql_type; 
  } 

    public void readSQL(SQLInput stream, String typeName)
    throws SQLException
  {
    sql_type = typeName;
    productoID = stream.readLong();
    cantidad = stream.readInt();
    precio=  stream.readLong();
    IVA=     stream.readLong();
  }
 
  public void writeSQL(SQLOutput stream)
    throws SQLException
  { 
     stream.writeLong(productoID);
     stream.writeLong(cantidad);
     stream.writeLong(precio);
     stream.writeLong(IVA);
  }
}
