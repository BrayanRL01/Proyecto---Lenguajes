package com.aplicacion.negocio.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.Roles;

import oracle.jdbc.OracleTypes;

@Service
public class RolesService {

    JDBCconnection JDBC = new JDBCconnection();

    public List<Roles> ObtenerRoles() throws SQLException {
        List<Roles> ArrayRoles = new ArrayList<>();

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_OBTENER_ROLES (?,?,?); END;");

        JDBC.call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        JDBC.call.registerOutParameter(2, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);

        JDBC.call.execute();

        ResultSet RS = (ResultSet) JDBC.call.getObject(1);

        while (RS.next()) {
            Roles R = new Roles(
                    RS.getLong(1),
                    RS.getString(2));
            ArrayRoles.add(R);
        }
        RS.close();
        JDBC.call.close();
        JDBC.close();

        return ArrayRoles;
    }

    public Roles ObtenerRolPorID(Long id) throws SQLException {

        Roles R;
        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_OBTENER_UN_ROLE (?,?,?,?); END;");

        JDBC.call.setLong(1, id);
        JDBC.call.registerOutParameter(2, OracleTypes.VARCHAR);
        JDBC.call.registerOutParameter(3, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(4, OracleTypes.VARCHAR);

        JDBC.call.execute();

        String N = (String) JDBC.call.getObject(2);

        R = new Roles(id, N);

        JDBC.call.close();
        JDBC.close();

        return R;
    }

    public void InsertarRoles(Roles R) throws SQLException {

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_INSERTAR_ROLE (?,?,?); END;");

        JDBC.call.setString(1, R.getNombre());
        JDBC.call.registerOutParameter(2, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);

        JDBC.call.execute();

        JDBC.call.close();
        JDBC.close();
    }

    public void ModificarRol(Roles R) throws SQLException {
        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_MODIFICAR_ROLE (?,?,?,?); END;");

        JDBC.call.setLong(1, R.getRol_Id());
        JDBC.call.setString(2, R.getNombre());
        JDBC.call.registerOutParameter(3, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(4, OracleTypes.VARCHAR);

        JDBC.call.execute();

    }

    public void EliminarRol(Long Id) throws SQLException {

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_ELIMINAR_ROLE (?,?,?); END;");

        JDBC.call.setLong(1, Id);
        JDBC.call.registerOutParameter(2, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);

        JDBC.call.execute();

        JDBC.call.close();
        JDBC.close();
    }
}
