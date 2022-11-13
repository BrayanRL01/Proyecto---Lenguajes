/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.service;

import com.aplicacion.negocio.entity.Personas;
import com.aplicacion.negocio.repository.PersonaRepository;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
public class PersonaService {
    @Autowired
    PersonaRepository personaRepository;
    
    public String lista() throws SQLException{
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

    // Connect to the database
    // You can put a database name after the @ sign in the connection URL.
    Connection conn =
    DriverManager.getConnection ("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "WEB_ACCESS", "ExternalWeb22");

    // Prepare a PL/SQL call
    CallableStatement call =
      conn.prepareCall ("BEGIN NEGOCIO.SP_OBTENER_PERSONAS (?); END;");

    // Find out all the SALES person
    call.registerOutParameter (1,OracleTypes.REF_CURSOR); /*OracleTypes.CURSOR*/
    call.execute();
    //String result = call.getString(2); 
    
    ResultSet rset = (ResultSet)call.getObject (1);
    
    //System.out.println ("resultadoioooooo> "+result);
    
    //Dump the cursor
    String var="empty";
        while (rset.next()) {

            String nombre = rset.getString("NOMBRE");
            String apellido1 = rset.getString("PRIMER_APELLIDO");
            String apellido2 = rset.getString("SEGUNDO_APELLIDO");
            String direccion = rset.getString("DIRECCION");
            String mail = rset.getString("EMAIL");
            String telefono = rset.getString("TELEFONO");
            String tipoP = rset.getString("TIPO_PERSONA");
            rset.next();
            
    // PER.NOMBRE, PER.PRIMER_APELLIDO, PER.SEGUNDO_APELLIDO, PER.DIRECCION, PER.EMAIL, PER.TELEFONO, PER.TIPO_PERSONA     
            var += ("\n"+"Email: "+mail+", "+
            "Nombre: "+nombre+", "+
            "apellido1: "+apellido1+", "+
            "apellido2: "+apellido2+", "+        
            "direccion: "+direccion+", "+
            "telefono: "+telefono+", "+
            "tipoP: "+tipoP      
            ); // puede llamar por columna o por numero de columna
            rset.next();
        }
    //Close all the resources
    rset.close();
    call.close();
    conn.close();
        return var;
    }
}
