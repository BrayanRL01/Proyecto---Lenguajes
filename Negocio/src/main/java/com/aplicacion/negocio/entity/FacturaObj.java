/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.entity;

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
    
    

    public FacturaObj(String sql_type) {
        this.sql_type = sql_type;
        objdetalleobjeto = new ArrayList<>();
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
}
