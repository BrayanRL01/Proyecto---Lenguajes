package com.aplicacion.negocio.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.Mensaje;
import com.aplicacion.negocio.entity.Usuarios;

import oracle.jdbc.OracleTypes;

@Service
public class UsuariosService {

    JDBCconnection DB = new JDBCconnection();

    Mensaje M = new Mensaje();

    public List<Usuarios> ObtenerUsuarios() throws SQLException {

        List<Usuarios> LU = new ArrayList<>();

        DB.init();

        DB.prepareCall("BEGIN NEGOCIO.SP_OBTENER_USUARIOS(?,?,?); END;");

        DB.call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        DB.call.registerOutParameter(2, OracleTypes.NUMBER);
        DB.call.registerOutParameter(3, OracleTypes.VARCHAR);

        DB.call.execute();

        ResultSet RS = (ResultSet) DB.call.getObject(1);

        while (RS.next()) {
            Usuarios U = new Usuarios(
                    RS.getLong(1),
                    RS.getString(2),
                    RS.getString(3),
                    RS.getString(4),
                    RS.getString(5),
                    RS.getString(6),
                    RS.getString(7),
                    RS.getString(8),
                    RS.getString(9));
            LU.add(U);
        }

        RS.close();
        DB.call.close();
        DB.close();

        return LU;
    }

    public Usuarios ObtenerUsuarioPorID(Long Id) throws SQLException {
        Usuarios U = new Usuarios();

        DB.init();

        DB.prepareCall("BEGIN NEGOCIO.SP_OBTENER_UN_USUARIO(?,?,?,?); END;");

        DB.call.setLong(1, Id);
        DB.call.registerOutParameter(2, OracleTypes.REF_CURSOR);
        DB.call.registerOutParameter(3, OracleTypes.NUMBER);
        DB.call.registerOutParameter(4, OracleTypes.VARCHAR);

        DB.call.execute();
        ResultSet RS = (ResultSet) DB.call.getObject(2);

        while (RS.next()) {
            U = new Usuarios(
                    RS.getLong(1),
                    RS.getString(2),
                    RS.getString(3),
                    RS.getString(4),
                    RS.getString(5),
                    RS.getString(6),
                    RS.getString(7),
                    RS.getString(8),
                    RS.getLong(9),
                    RS.getLong(10));
        }

        DB.call.close();
        DB.close();

        return U;
    }

    public Mensaje InsertarUsuario(Usuarios U) throws SQLException {
        DB.init();

        DB.prepareCall("BEGIN NEGOCIO.SP_INSERTAR_USUARIO (?,?,?,?,?,?,?,?,?,?,?); END;");

        DB.call.setString(1, U.getNickname());
        DB.call.setString(2, U.getNombre());
        DB.call.setString(3, U.getPrimer_Apellido());
        DB.call.setString(4, U.getSegundo_Apellido());
        DB.call.setString(5, U.getEmail());
        DB.call.setString(6, U.getContrasena());
        DB.call.setString(7, U.getTelefono());
        DB.call.setLong(8, U.getRol_Id());
        DB.call.setLong(9, U.getEstado_Usuario_Id());
        DB.call.registerOutParameter(10, OracleTypes.NUMBER);
        DB.call.registerOutParameter(11, OracleTypes.VARCHAR);

        DB.call.execute();

        M.setNumero(DB.call.getInt(10));
        M.setMensaje(DB.call.getString(11));

        DB.call.close();
        DB.close();

        return M;
    }

    public Mensaje ModificarUsuario(Usuarios U) throws SQLException {
        DB.init();

        DB.prepareCall("BEGIN NEGOCIO.SP_MODIFICAR_USUARIO (?,?,?,?,?,?,?,?,?,?,?,?); END;");

        DB.call.setLong(1, U.getUsuario_Id());
        DB.call.setString(2, U.getNickname());
        DB.call.setString(3, U.getNombre());
        DB.call.setString(4, U.getPrimer_Apellido());
        DB.call.setString(5, U.getSegundo_Apellido());
        DB.call.setString(6, U.getEmail());
        DB.call.setString(7, U.getContrasena());
        DB.call.setString(8, U.getTelefono());
        DB.call.setLong(9, U.getRol_Id());
        DB.call.setLong(10, U.getEstado_Usuario_Id());
        DB.call.registerOutParameter(11, OracleTypes.NUMBER);
        DB.call.registerOutParameter(12, OracleTypes.VARCHAR);

        DB.call.execute();

        M.setNumero(DB.call.getInt(11));
        M.setMensaje(DB.call.getString(12));

        return M;
    }

    public Mensaje EliminarUsuario(Long Id) throws SQLException {

        DB.init();

        DB.prepareCall("BEGIN NEGOCIO.SP_ELIMINAR_USUARIO(?,?,?); END;");

        DB.call.setLong(1, Id);
        DB.call.registerOutParameter(2, OracleTypes.NUMBER);
        DB.call.registerOutParameter(3, OracleTypes.VARCHAR);

        DB.call.execute();

        M.setNumero(DB.call.getInt(2));
        M.setMensaje(DB.call.getString(3));

        DB.call.close();
        DB.close();

        return M;
    }
}
