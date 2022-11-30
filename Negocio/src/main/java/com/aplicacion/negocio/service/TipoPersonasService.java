package com.aplicacion.negocio.service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.Tipo_Personas;
import java.math.BigDecimal;
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
public class TipoPersonasService {

    JDBCconnection jdbc = new JDBCconnection();

    public List<Tipo_Personas> obtenerTipoPersonas() throws SQLException {
        // crear lista que el metodo va devolver
        List<Tipo_Personas> contenedor = new ArrayList<>();

        // Connect to the database
        jdbc.init();

        // Prepare a PL/SQL call
        jdbc.prepareCall("BEGIN NEGOCIO.SP_OBTENER_TIPOS_PERSONA (?,?,?); END;");
       
        // se le indica la posicion del parametro y el tipo
        jdbc.call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        jdbc.call.registerOutParameter(2, OracleTypes.NUMBER);
        jdbc.call.registerOutParameter(3, OracleTypes.VARCHAR);
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

    public void saveTPersonas(Tipo_Personas nom) throws SQLException {
        jdbc.init();

        // Prepare a PL/SQL call
        jdbc.prepareCall("BEGIN NEGOCIO.SP_INSERTAR_TIPO_PERSONA (?,?,?); END;");
        
        jdbc.call.setString(1, nom.getNombre());
        jdbc.call.registerOutParameter(2, OracleTypes.NUMBER);
        jdbc.call.registerOutParameter(3, OracleTypes.VARCHAR);

        jdbc.call.execute();
        
        BigDecimal rset = (BigDecimal) jdbc.call.getObject(2);
        System.out.println(rset);

        jdbc.call.close();
        jdbc.close();
    }

    public Tipo_Personas getTPersonaPorID(long id_Tpersona) throws SQLException {
        Tipo_Personas per = new Tipo_Personas();

        jdbc.init();

        // Prepare a PL/SQL call
        jdbc.prepareCall("BEGIN NEGOCIO.SP_OBTENER_UN_TIPO_PERSONA (?,?,?,?); END;");

        jdbc.call.setLong(1, id_Tpersona);
        jdbc.call.registerOutParameter(2, OracleTypes.REF_CURSOR);
        jdbc.call.registerOutParameter(3, OracleTypes.NUMBER);
        jdbc.call.registerOutParameter(4, OracleTypes.VARCHAR);
        // se ejecuta el query
        jdbc.call.execute();

        // se almacena el resultado del query en rset
        ResultSet rset = (ResultSet) jdbc.call.getObject(2);

        while (rset.next()) {
            Long nombre = rset.getLong("ID_TIPO_PERSONA");
            String cedula = rset.getString("NOMBRE");

            System.out.println(nombre + ", " + cedula);

            per = new Tipo_Personas(
                    rset.getLong(1),
                    rset.getString(2)
            );
        }
        jdbc.call.close();
        jdbc.close();

        return per;
    }
    
    public void actualizarTPersona(Tipo_Personas tper) throws SQLException {
        jdbc.init();

        // Prepare a PL/SQL call
        jdbc.prepareCall("BEGIN NEGOCIO.SP_MODIFICAR_TIPO_PERSONA (?,?,?); END;");

        jdbc.call.setLong(1, tper.getId_tipo_persona());
        jdbc.call.setString(2, tper.getNombre());
        jdbc.call.registerOutParameter(3, OracleTypes.NUMBER);

        // se ejecuta el query
        jdbc.call.execute();

        jdbc.call.close();
        jdbc.close();
    }

    public void eliminarTPersona(long id_usuario) throws SQLException {
        jdbc.init();

        // Prepare a PL/SQL call
        jdbc.prepareCall("BEGIN NEGOCIO.SP_ELIMINAR_TIPO_PERSONA (?,?,?); END;");

        jdbc.call.setLong(1, id_usuario);
        jdbc.call.registerOutParameter(2, OracleTypes.NUMBER);
        jdbc.call.registerOutParameter(3, OracleTypes.VARCHAR);

        // se ejecuta el query
        jdbc.call.execute();

        // se almacena el resultado del query en rset
        BigDecimal rset = (BigDecimal) jdbc.call.getObject(2);

        System.out.println("Resultado de borrar: " + rset);

        jdbc.call.close();
        jdbc.close();
    }

}
