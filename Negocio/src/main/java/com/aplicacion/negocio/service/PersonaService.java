/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.service;

import com.aplicacion.negocio.controller.JdbcUtil;
import com.aplicacion.negocio.entity.Personas;
import com.aplicacion.negocio.entity.Tipo_Personas;
import com.aplicacion.negocio.repository.PersonaRepository;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public /*String List<Map<String,?>>*/ List<Personas> lista() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        List<Personas> personas = new ArrayList<>();
        // Connect to the database
        // You can put a database name after the @ sign in the connection URL.
        Connection conn
                = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "WEB_ACCESS", "ExternalWeb22");

        // Prepare a PL/SQL call
        CallableStatement call
                = conn.prepareCall("BEGIN NEGOCIO.SP_OBTENER_PERSONAS (?); END;");

        // Find out all the SALES person
        call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        /*OracleTypes.CURSOR*/
        call.execute();
        //String result = call.getString(2); 

        ResultSet rset = (ResultSet) call.getObject(1);
        
        ResultSetMetaData rsmd = rset.getMetaData();
        String name = "1"+rsmd.getColumnName(1);
         name += " 2 "+rsmd.getColumnName(2);
         name += " 3 "+rsmd.getColumnName(3);
         name += " 4 "+rsmd.getColumnName(4);
         name += " 5 "+rsmd.getColumnName(5);
         name += " 6 "+rsmd.getColumnName(6);
         name += " 7 "+rsmd.getColumnName(7);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAA "+name);
        
        //System.out.println ("resultadoioooooo> "+result);
        //List<Map<String,?>> lista = JdbcUtil.rsToList(rset);
        //Dump the cursor
        String var = "empty";
        while (rset.next()) {
            /*
            String nombre = rset.getString("NOMBRE");
            Long cedula = rset.getLong("CEDULA");
            String apellido1 = rset.getString("PRIMER_APELLIDO");
            String apellido2 = rset.getString("SEGUNDO_APELLIDO");
            String direccion = rset.getString("DIRECCION");
            String mail = rset.getString("EMAIL");
            String telefono = rset.getString("TELEFONO");
            String tipoP = rset.getString("TIPO_PERSONA");
            System.out.println("dsadadsdaasdsaasds "+cedula);
            
    // PER.NOMBRE, PER.PRIMER_APELLIDO, PER.SEGUNDO_APELLIDO, PER.DIRECCION, PER.EMAIL, PER.TELEFONO, PER.TIPO_PERSONA     
           /* var += ("Email: "+mail+", "+
            "Nombre: "+nombre+", "+
            "apellido1: "+apellido1+", "+
            "apellido2: "+apellido2+", "+        
            "direccion: "+direccion+", "+
            "telefono: "+telefono+", "+
            "tipoP: "+tipoP+"\n"      
            ); // puede llamar por columna o por numero de columna
             */
            // Long cedula, String nombre, String primerAp, String segundoAp, String direccion, String email, String telefono
            Personas per = new Personas(
                    rset.getLong(1),
                    rset.getString(2),
                    rset.getString(3),
                    rset.getString(4),
                    rset.getString(5),
                    rset.getString(6),
                    rset.getString(7)
            );
            personas.add(per);
            
        }
        //Close all the resources
        rset.close();
        call.close();
        conn.close();
        return personas;
    }
}
