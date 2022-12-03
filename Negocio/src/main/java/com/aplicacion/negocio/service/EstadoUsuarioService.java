package com.aplicacion.negocio.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.EstadoUsuario;
import com.aplicacion.negocio.entity.Mensaje;

import oracle.jdbc.OracleTypes;

@Service
public class EstadoUsuarioService {
    
    JDBCconnection JDBC = new JDBCconnection();

    Mensaje M = new Mensaje();

    public List<EstadoUsuario> ObtenerEstados() throws SQLException {
        List<EstadoUsuario> ArrayEstados = new ArrayList<>();

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_OBTENER_ESTADOS_USUARIO (?,?,?); END;");

        JDBC.call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        JDBC.call.registerOutParameter(2, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);

        JDBC.call.execute();

        ResultSet RS = (ResultSet) JDBC.call.getObject(1);

        while (RS.next()) {
            EstadoUsuario EU = new EstadoUsuario(
                    RS.getLong(1),
                    RS.getString(2));
            ArrayEstados.add(EU);
        }
        RS.close();
        JDBC.call.close();
        JDBC.close();

        return ArrayEstados;
    }

    public EstadoUsuario ObtenerEstadoPorID(Long id) throws SQLException {

        EstadoUsuario EU;
        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_OBTENER_UN_ESTADO_USUARIO (?,?,?,?); END;");

        JDBC.call.setLong(1, id);
        JDBC.call.registerOutParameter(2, OracleTypes.VARCHAR);
        JDBC.call.registerOutParameter(3, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(4, OracleTypes.VARCHAR);

        JDBC.call.execute();

        String N = (String) JDBC.call.getObject(2);

        EU = new EstadoUsuario(id, N);

        JDBC.call.close();
        JDBC.close();

        return EU;
    }

    public Mensaje InsertarEstados(EstadoUsuario EU) throws SQLException {

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_INSERTAR_ESTADO_USUARIO (?,?,?); END;");

        JDBC.call.setString(1, EU.getNombre());
        JDBC.call.registerOutParameter(2, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);

        JDBC.call.execute();

        M.setNumero(JDBC.call.getInt(2));
        M.setMensaje(JDBC.call.getString(3));

        JDBC.call.close();
        JDBC.close();

        return M;
    }

    public Mensaje ModificarEstado(EstadoUsuario EU) throws SQLException {
        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_MODIFICAR_ESTADO_USUARIO (?,?,?,?); END;");

        JDBC.call.setLong(1, EU.getEstado_Usuario_Id());
        JDBC.call.setString(2, EU.getNombre());
        JDBC.call.registerOutParameter(3, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(4, OracleTypes.VARCHAR); 

        JDBC.call.execute();

        M.setNumero(JDBC.call.getInt(3));
        M.setMensaje(JDBC.call.getString(4));

        return M;

    }

    public Mensaje EliminarEstado(Long Id) throws SQLException {

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_ELIMINAR_ESTADO_USUARIO (?,?,?); END;");

        JDBC.call.setLong(1, Id);
        JDBC.call.registerOutParameter(2, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);

        JDBC.call.execute();

        M.setNumero(JDBC.call.getInt(2));
        M.setMensaje(JDBC.call.getString(3));

        JDBC.call.close();
        JDBC.close();

        return M;
    }

}
