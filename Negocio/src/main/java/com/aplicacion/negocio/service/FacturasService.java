/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.Facturas;
import java.sql.ResultSet;
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

    public List<Facturas> obtenerFacturasSinDetalle() throws SQLException {
        // crear lista que el metodo va devolver
        List<Facturas> contenedor = new ArrayList<>();

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
        /*
         * ResultSetMetaData rsmd = rset.getMetaData();
         * String name = "1-" + rsmd.getColumnName(1);
         * name += " 2-" + rsmd.getColumnName(2);
         * name += " 3-" + rsmd.getColumnName(3);
         * name += " 4-" + rsmd.getColumnName(4);
         * name += " 5-" + rsmd.getColumnName(5);
         * name += " 6-" + rsmd.getColumnName(6);
         * name += " 7-" + rsmd.getColumnName(7);
         * name += " 8-" + rsmd.getColumnName(8);
         * name += " 9-" + rsmd.getColumnName(9);
         * //name += " 9-" + rsmd.getColumnName(10);
         * System.out.println(name);
         */

        // Dump the cursor
        while (rset.next()) {
            Facturas per = new Facturas(
                    rset.getLong(1),
                    rset.getLong(2),
                    rset.getLong(3),
                    rset.getLong(4),
                    rset.getLong(5),
                    rset.getLong(6),
                    rset.getLong(7),
                    rset.getString(8));
            contenedor.add(per);
        }
        // Close all the resources
        rset.close();
        jdbc.call.close();
        jdbc.close();
        return contenedor;
    }
}
