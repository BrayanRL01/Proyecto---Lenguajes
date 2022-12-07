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
    private Long cantidad;
    private BigDecimal precio;
    private BigDecimal IVA;
    
    //String sql_type, Long productoID, Long cantidad, Float precio, Float IVA
    public DetalleObj(String sql_type, Long productoID, Long cantidad, BigDecimal precio, BigDecimal IVA) {
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

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getIVA() {
        return IVA;
    }

    public void setIVA(BigDecimal IVA) {
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
    cantidad = stream.readLong();
    precio=  stream.readBigDecimal();
    IVA=     stream.readBigDecimal();
  }
 
  public void writeSQL(SQLOutput stream)
    throws SQLException
  { 
     stream.writeLong(productoID);
     stream.writeLong(cantidad);
     stream.writeBigDecimal(precio);
     stream.writeBigDecimal(IVA);
  }
}
