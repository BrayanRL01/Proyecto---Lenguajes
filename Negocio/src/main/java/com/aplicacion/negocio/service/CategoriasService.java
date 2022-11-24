package com.aplicacion.negocio.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.Categorias;

import oracle.jdbc.internal.OracleTypes;

@Service
public class CategoriasService {

    JDBCconnection JDBC = new JDBCconnection();

    public List<Categorias> ObtenerCategorias() throws SQLException {

        List<Categorias> LC = new ArrayList<>();

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_OBTENER_CATEGORIAS_PRINCIPALES (?,?,?); END;");

        JDBC.call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        JDBC.call.registerOutParameter(2, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);

        JDBC.call.execute();

        ResultSet RS = (ResultSet) JDBC.call.getObject(1);

        while (RS.next()) {
            Categorias C = new Categorias(
                    RS.getLong(1),
                    RS.getString(2),
                    RS.getLong(3));
            LC.add(C);
        }

        RS.close();
        JDBC.call.close();
        JDBC.close();

        return LC;
    }

    public List<Categorias> ObtenerSubCategorias() throws SQLException {
        Categorias C;
        List<Categorias> LC = new ArrayList<>();

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_OBTENER_SUBCATEGORIAS_EN_CATEGORIA (?,?,?); END;");

        JDBC.call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        JDBC.call.registerOutParameter(2, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);
        JDBC.call.execute();

        ResultSet RS = (ResultSet) JDBC.call.getObject(1);

        while (RS.next()) {
            C = new Categorias(
                    RS.getLong(1),
                    RS.getString(2),
                    RS.getLong(3));
            LC.add(C);
        }
        // Close all the resources
        RS.close();
        JDBC.call.close();
        JDBC.close();
        return LC;
    }

    public Categorias ObtenerCategoriasPorID(Long id) throws SQLException {
        Categorias C = new Categorias();

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_OBTENER_UNA_CATEGORIA (?,?); END;");

        JDBC.call.setLong(1, id);
        JDBC.call.registerOutParameter(2, OracleTypes.REF_CURSOR);

        JDBC.call.execute();

        ResultSet N = (ResultSet) JDBC.call.getObject(2);
        while (N.next()) {
            C = new Categorias(N.getLong(1), N.getString(2), N.getLong(3));
        }

        // C = new Categorias(N.getLong(1), N.getString(2), N.getLong(3));

        JDBC.call.close();
        JDBC.close();

        return C;
    }

    public void InsertarCategorias(Categorias C) throws SQLException {
        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_INSERTAR_CATEGORIA (?,?,?); END;");

        JDBC.call.setString(1, C.getNombre());
        JDBC.call.registerOutParameter(2, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);

        JDBC.call.execute();

        JDBC.call.close();
        JDBC.close();
    }

    public void InsertarSubCategorias(Categorias C) throws SQLException {
        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_INSERTAR_SUBCATEGORIA (?,?,?,?); END;");

        JDBC.call.setString(1, C.getNombre());
        JDBC.call.setLong(2, C.getCategoria_Madre_Id());
        JDBC.call.registerOutParameter(3, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(4, OracleTypes.VARCHAR);

        JDBC.call.execute();

        JDBC.call.close();
        JDBC.close();
    }

    public void ModificarCategoria(Categorias C) throws SQLException {
        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_MODIFICAR_CATEGORIA (?,?,?,?,?); END;");

        JDBC.call.setLong(1, C.getId_Categoria());
        JDBC.call.setString(2, C.getNombre());
        JDBC.call.setLong(3, C.getCategoria_Madre_Id());
        JDBC.call.registerOutParameter(4, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(5, OracleTypes.VARCHAR);

        JDBC.call.execute();
    }

    public void EliminarCategoria(Long ID) throws SQLException {

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_ELIMINAR_CATEGORIA (?,?,?); END;");

        JDBC.call.setLong(1, ID);
        JDBC.call.registerOutParameter(2, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);

        JDBC.call.execute();

        JDBC.call.close();
        JDBC.close();

    }

}
