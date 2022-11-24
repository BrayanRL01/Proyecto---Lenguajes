package com.aplicacion.negocio.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.Productos;

import oracle.jdbc.OracleTypes;

@Service
public class ProductosService {

    JDBCconnection DB = new JDBCconnection();

    public List<Productos> ObtenerProductos() throws SQLException {

        List<Productos> LP = new ArrayList<>();

        DB.init();

        DB.prepareCall("BEGIN NEGOCIO.SP_OBTENER_PRODUCTOS(?); END;");
        DB.call.registerOutParameter(1, OracleTypes.REF_CURSOR);

        DB.call.execute();

        ResultSet RS = (ResultSet) DB.call.getObject(1);
  
        while (RS.next()) {
            Productos P = new Productos(
                    RS.getLong(1),
                    RS.getString(2),
                    RS.getLong(3),
                    RS.getLong(4),
                    RS.getString(5),
                    RS.getString(6),
                    RS.getLong(7),
                    RS.getString(8),
                    RS.getLong(9)
                );
                    LP.add(P);
        }

        RS.close();
        DB.call.close();
        DB.close();

        return LP;
    }
}
