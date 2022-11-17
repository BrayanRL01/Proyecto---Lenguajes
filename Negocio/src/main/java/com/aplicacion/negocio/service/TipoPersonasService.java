/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.Tipo_Personas;
import com.aplicacion.negocio.repository.TipoPersonasRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    JDBCconnection jdbc = new JDBCconnection();

    public List<Tipo_Personas> obtenerTipoPersonas() throws SQLException {
        //crear lista que el metodo va devolver 
        List<Tipo_Personas> contenedor = new ArrayList<>();

        // Connect to the database
        jdbc.init();

                // Prepare a PL/SQL call
        jdbc.prepareCall("BEGIN NEGOCIO.SP_OBTENER_TIPOS_PERSONA (?); END;");

        // se le indica la posicion del parametro y el tipo
        jdbc.call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        // se ejecuta el query
        jdbc.call.execute();
        // rset guarda el resultado del llamado
        ResultSet rset = (ResultSet) jdbc.call.getObject(1);
        
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
            // 1-ID_PERSONA 2-CEDULA 3-NOMBRE 4-PRIMER_APELLIDO 5-SEGUNDO_APELLIDO 6-DIRECCION 7-EMAIL 8-TELEFONO
            // Long id_persona, Long cedula, String nombre, String primerAp, String segundoAp, String direccion, String email, String telefono
            Tipo_Personas per = new Tipo_Personas(
                    rset.getLong(1),
                    rset.getString(2)
            );
            contenedor.add(per);
        }
        //Close all the resources
        rset.close();
        jdbc.call.close();
        jdbc.close();
        
        return contenedor;
    }
}
