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
import java.util.ArrayList;

/**
 *
 * @author XPC
 */
public class FacturaObj implements SQLData {

    private String sql_type;
    
    private ArrayList<DetalleObj> objdetalleobjeto;
    
    private Long idVendedor;
    private Long idCliente;
    private Long tipoVenta;
    private Long medioPago;
    private BigDecimal totalEntrega;

    public FacturaObj(String sql_type, ArrayList<DetalleObj> objdetalleobjeto, Long idVendedor, Long idCliente, Long tipoVenta, Long medioPago, BigDecimal totalEntrega) {
        this.sql_type = sql_type;
        this.objdetalleobjeto = objdetalleobjeto;
        this.idVendedor = idVendedor;
        this.idCliente = idCliente;
        this.tipoVenta = tipoVenta;
        this.medioPago = medioPago;
        this.totalEntrega = totalEntrega;
    }
    public FacturaObj(){
    
    }
    
    @Override
    public String getSQLTypeName() throws SQLException {
        return this.sql_type;
    }

    @Override
    public void readSQL(SQLInput sqli, String string) throws SQLException {
        
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        
    }
    public void add(DetalleObj e){
        objdetalleobjeto.add(e);
    }

    public String getSql_type() {
        return sql_type;
    }

    public void setSql_type(String sql_type) {
        this.sql_type = sql_type;
    }

    public ArrayList<DetalleObj> getObjdetalleobjeto() {
        return objdetalleobjeto;
    }

    public void setObjdetalleobjeto(ArrayList<DetalleObj> objdetalleobjeto) {
        this.objdetalleobjeto = objdetalleobjeto;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Long idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(Long tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public Long getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(Long medioPago) {
        this.medioPago = medioPago;
    }

    public BigDecimal getTotalEntrega() {
        return totalEntrega;
    }

    public void setTotalEntrega(BigDecimal totalEntrega) {
        this.totalEntrega = totalEntrega;
    }
    
    
}
