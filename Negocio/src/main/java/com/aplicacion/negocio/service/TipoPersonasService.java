/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.service;

import com.aplicacion.negocio.entity.Tipo_Personas;
import com.aplicacion.negocio.repository.TipoPersonasRepository;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author XPC
 */
@Service
public class TipoPersonasService {
    
    @Autowired
    TipoPersonasRepository tpRepository;
    
    public String lista() throws SQLException{
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

    // Connect to the database
    // You can put a database name after the @ sign in the connection URL.
    Connection conn =
    DriverManager.getConnection ("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "WEB_ACCESS", "ExternalWeb22");

    // Prepare a PL/SQL call
    CallableStatement call =
      conn.prepareCall ("BEGIN NEGOCIO.SP_GET_TIPO_PERSONAS (?,?); END;");

    // Find out all the SALES person
    call.setInt(1,1);
    call.registerOutParameter (2,OracleTypes.VARCHAR); /*OracleTypes.CURSOR*/
    call.execute();
    String result = call.getString(2); 
    
    /*ResultSet rset = (ResultSet)call.getObject (2);*/
    
    System.out.println ("resultadoioooooo"+result);
    
    // Dump the cursor
    
    /*while (rset.next ())
      System.out.println (rset.getString ("ENAME"));*/

    // Close all the resources
    /*rset.close();*/   
    call.close();
    conn.close();
        
        return result;
    }
}
