/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.DetalleVista;
import com.aplicacion.negocio.entity.FacturaVista;
import com.aplicacion.negocio.entity.Facturas;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.springframework.stereotype.Service;

/**
 *
 * @author XPC
 */
@Service
public class FacturasService {

    JDBCconnection jdbc = new JDBCconnection();

    public List<FacturaVista> obtenerFacturasSinDetalle() throws SQLException {
        // crear lista que el metodo va devolver
        List<FacturaVista> contenedor = new ArrayList<>();

        // Connect to the database
        jdbc.init();

        // Prepare a PL/SQL call
        jdbc.prepareCall("BEGIN NEGOCIO.SP_OBTENER_FACTURAS (?); END;");

        // se le indica la posicion del parametro y el tipo
        jdbc.call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        // se ejecuta el query
        jdbc.call.execute();
        // rset guarda el resultado del llamado
         ResultSet rset = (ResultSet) jdbc.call.getObject(1);
        // como ver el nombre de las columnas
        
         //ResultSetMetaData rsmd = rset.getMetaData();
         
         

        // Dump the cursor
        while (rset.next()) {
            
            String name = "1-" + rset.getLong(1);
         name += " 2-" + rset.getString(2);
         name += " 3-" + rset.getString(3);
         name += " 4-" + rset.getString(4);
         name += " 5-" + rset.getLong(5);
         name += " 6-" + rset.getLong(6);
         name += " 7-" + rset.getString(7);
         name += " 8-" + rset.getString(8);
         System.out.println(name);
            
            
            
            FacturaVista per = new FacturaVista(
                    rset.getLong(1),
                    rset.getString(2),
                    rset.getString(3),
                    rset.getString(4),
                    rset.getLong(5),
                    rset.getLong(6),
                    rset.getString(7),
                    rset.getString(8));
            contenedor.add(per);
        }
        // Close all the resources
        rset.close();
        jdbc.call.close();
        jdbc.close();
        return contenedor;
    }

    public List<DetalleVista> obtenerFactconDetalles(Long id_factura) throws SQLException {
            // crear lista que el metodo va devolver
        List<DetalleVista> contenedor = new ArrayList<>();

        // Connect to the database
        jdbc.init();

        // Prepare a PL/SQL call
        jdbc.prepareCall("BEGIN NEGOCIO.SP_OBTENER_FACTURA_CON_DETALLES (?,?,?,?,?,?,?,?,?,?,?); END;");

        // se le indica la posicion del parametro y el tipo
        
        jdbc.call.setLong(1, id_factura); //id factura
        jdbc.call.registerOutParameter(2, OracleTypes.NUMBER); //out id factura
        jdbc.call.registerOutParameter(3, OracleTypes.VARCHAR); //vendedor
        jdbc.call.registerOutParameter(4, OracleTypes.VARCHAR); //cliente
        jdbc.call.registerOutParameter(5, OracleTypes.VARCHAR); //tipo venta
        jdbc.call.registerOutParameter(6, OracleTypes.DECIMAL); //total entrega
        jdbc.call.registerOutParameter(7, OracleTypes.DECIMAL); //total
        jdbc.call.registerOutParameter(8, OracleTypes.VARCHAR); //medio de pago
        jdbc.call.registerOutParameter(9, OracleTypes.TIMESTAMP); //fecha 
        jdbc.call.registerOutParameter(10, OracleTypes.REF_CURSOR); //detalles
        jdbc.call.registerOutParameter(10, OracleTypes.NUMBER); //resultado
        
        
        jdbc.call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        // se ejecuta el query
        jdbc.call.execute();
        // rset guarda el resultado del llamado
         ResultSet rset = (ResultSet) jdbc.call.getObject(10);
        // como ver el nombre de las columnas
         

        // Dump the cursor
        while (rset.next()) {
            
        }
        // Close all the resources
        rset.close();
        jdbc.call.close();
        jdbc.close();
        return contenedor;
    }
    
}
