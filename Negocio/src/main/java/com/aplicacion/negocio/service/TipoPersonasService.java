package com.aplicacion.negocio.service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.Tipo_Personas;
import com.aplicacion.negocio.repository.TipoPersonasRepository;

import java.math.BigDecimal;
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
                // crear lista que el metodo va devolver
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
                        Tipo_Personas per = new Tipo_Personas(
                                        rset.getLong(1),
                                        rset.getString(2));
                        contenedor.add(per);
                }
                // Close all the resources
                rset.close();
                jdbc.call.close();
                jdbc.close();

                return contenedor;
        }

}
