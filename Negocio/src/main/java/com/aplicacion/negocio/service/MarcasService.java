package com.aplicacion.negocio.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.Marcas;
import oracle.jdbc.OracleTypes;

@Service
public class MarcasService {

    JDBCconnection JDBC = new JDBCconnection();

    public List<Marcas> ObtenerMarcas() throws SQLException {
        // Crea la lista para los objetos.
        List<Marcas> ArrayMarcas = new ArrayList<>();

        // Realiza la conexión a la DB.
        JDBC.init();

        // Prepara el llamado al procedimiento.
        JDBC.prepareCall("BEGIN NEGOCIO.SP_OBTENER_MARCAS (?); END;");

        // Se le indica la posición del parametro y el tipo.
        JDBC.call.registerOutParameter(1, OracleTypes.REF_CURSOR);

        // Se ejecuta el query.
        JDBC.call.execute();

        // RS guarda el resultado del llamado.
        ResultSet RS = (ResultSet) JDBC.call.getObject(1);

        while (RS.next()) {
            Marcas M = new Marcas(
                    RS.getLong(1),
                    RS.getString(2));
            ArrayMarcas.add(M);
        }
        // Close all the resources
        RS.close();
        JDBC.call.close();
        JDBC.close();

        return ArrayMarcas;
    }

}
