package com.aplicacion.negocio.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.Mensaje;
import com.aplicacion.negocio.entity.Personas;

import oracle.jdbc.OracleTypes;

/**
 *
 * @author XPC
 */
@Service
public class PersonaService {

    // instancia para la conexion a la BD
    JDBCconnection jdbc = new JDBCconnection();

    Mensaje msj = new Mensaje();

    public List<Personas> obtenerPersonas() throws SQLException {

        // crear lista que el metodo va devolver
        List<Personas> contenedor = new ArrayList<>();

        // Connect to the database
        jdbc.init();

        // Prepare a PL/SQL call
        jdbc.prepareCall("BEGIN NEGOCIO.SP_OBTENER_PERSONAS (?,?,?); END;");

        // se le indica la posicion del parametro y el tipo
        jdbc.call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        jdbc.call.registerOutParameter(2, OracleTypes.NUMBER);
        jdbc.call.registerOutParameter(3, OracleTypes.VARCHAR);
        // se ejecuta el query
        jdbc.call.execute();
        // rset guarda el resultado del llamado
        ResultSet rset = (ResultSet) jdbc.call.getObject(1);

        while (rset.next()) {

            Personas per = new Personas(
                    rset.getLong(1),
                    rset.getLong(2),
                    rset.getString(3),
                    rset.getString(4),
                    rset.getString(5),
                    rset.getString(6),
                    rset.getString(7),
                    rset.getString(8),
                    rset.getString(9));
            contenedor.add(per);
        }
        // Close all the resources
        rset.close();
        jdbc.call.close();
        jdbc.close();
        return contenedor;
    }

    public Mensaje savePersonas(Personas per) throws SQLException {
        jdbc.init();
        msj = new Mensaje();

        // Prepare a PL/SQL call
        jdbc.prepareCall("BEGIN NEGOCIO.PKG_PERSONAS.SP_INSERTAR_PERSONA (?,?,?,?,?,?,?,?,?,?); END;");

        jdbc.call.setLong(1, per.getCedula());
        jdbc.call.setString(2, per.getNombre());
        jdbc.call.setString(3, per.getPrimerAp());
        jdbc.call.setString(4, per.getSegundoAp());
        jdbc.call.setString(5, per.getDireccion());
        jdbc.call.setString(6, per.getEmail());
        jdbc.call.setString(7, per.getTelefono());
        jdbc.call.setLong(8, per.getTipoPersonaId());
        jdbc.call.registerOutParameter(9, OracleTypes.NUMBER); // worked or not
        jdbc.call.registerOutParameter(10, OracleTypes.VARCHAR); // mensaje de error

        jdbc.call.execute();

        msj.setNumero(jdbc.call.getInt(9));
        msj.setMensaje(jdbc.call.getString(10));

        jdbc.call.close();
        jdbc.close();

        return msj;
    }

    public Personas getPersonaPorID(Long id) throws SQLException {
        Personas per = new Personas();

        jdbc.init();

        // Prepare a PL/SQL call
        jdbc.prepareCall("BEGIN NEGOCIO.SP_OBTENER_UNA_PERSONA (?,?,?,?); END;");

        jdbc.call.setLong(1, id);
        jdbc.call.registerOutParameter(2, OracleTypes.REF_CURSOR);
        jdbc.call.registerOutParameter(3, OracleTypes.NUMBER);
        jdbc.call.registerOutParameter(4, OracleTypes.VARCHAR);

        // se ejecuta el query
        jdbc.call.execute();

        // se almacena el resultado del query en rset
        ResultSet rset = (ResultSet) jdbc.call.getObject(2);

        while (rset.next()) {
            per = new Personas(
                    rset.getLong(1),
                    rset.getLong(2),
                    rset.getString(3),
                    rset.getString(4),
                    rset.getString(5),
                    rset.getString(6),
                    rset.getString(7),
                    rset.getString(8),
                    rset.getString(9));
        }

        jdbc.call.close();
        jdbc.close();

        return per;
    }

    public Mensaje actualizarPersona(Personas per) throws SQLException {
        msj = new Mensaje();
        jdbc.init();

        // Prepare a PL/SQL call
        jdbc.prepareCall("BEGIN NEGOCIO.SP_MODIFICAR_PERSONA (?,?,?,?,?,?,?,?,?,?,?); END;");

        jdbc.call.setLong(1, per.getId_persona());
        jdbc.call.setLong(2, per.getCedula());
        jdbc.call.setString(3, per.getNombre());
        jdbc.call.setString(4, per.getPrimerAp());
        jdbc.call.setString(5, per.getSegundoAp());
        jdbc.call.setString(6, per.getDireccion());
        jdbc.call.setString(7, per.getEmail());
        jdbc.call.setString(8, per.getTelefono());
        jdbc.call.setLong(9, per.getTipoPersonaId());
        jdbc.call.registerOutParameter(10, OracleTypes.NUMBER);
        jdbc.call.registerOutParameter(11, OracleTypes.VARCHAR);

        jdbc.call.execute();

        msj.setNumero(jdbc.call.getInt(10));
        msj.setMensaje(jdbc.call.getString(11));

        jdbc.call.close();
        jdbc.close();

        return msj;
    }

    public Mensaje eliminarPersona(Long per) throws SQLException {
        msj = new Mensaje();

        jdbc.init();

        // Prepare a PL/SQL call
        jdbc.prepareCall("BEGIN NEGOCIO.SP_ELIMINAR_PERSONA (?,?,?); END;");

        jdbc.call.setLong(1, per);
        jdbc.call.registerOutParameter(2, OracleTypes.NUMBER);
        jdbc.call.registerOutParameter(3, OracleTypes.VARCHAR);

        // se ejecuta el query
        jdbc.call.execute();

        msj.setNumero(jdbc.call.getInt(2));
        msj.setMensaje(jdbc.call.getString(3));
        // System.out.println("+++++++++++++++++ Resultado de SP_ELIMINAR_PERSONA: " +
        // rset);
        jdbc.call.close();
        jdbc.close();

        return msj;
    }

}
