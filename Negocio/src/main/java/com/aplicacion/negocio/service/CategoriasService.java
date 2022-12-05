package com.aplicacion.negocio.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.Categorias;
import com.aplicacion.negocio.entity.Mensaje;

import oracle.jdbc.internal.OracleTypes;

@Service
public class CategoriasService {

    JDBCconnection JDBC = new JDBCconnection();

    Mensaje M = new Mensaje();

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
                    RS.getString(3));
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

        JDBC.call.close();
        JDBC.close();

        return C;
    }

    public Mensaje InsertarCategorias(Categorias C) throws SQLException {
        M = new Mensaje();

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_INSERTAR_CATEGORIA (?,?,?); END;");

        JDBC.call.setString(1, C.getNombre());
        JDBC.call.registerOutParameter(2, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);

        JDBC.call.execute();

        M.setNumero(JDBC.call.getInt(2));
        M.setMensaje(JDBC.call.getString(3));

        JDBC.call.close();
        JDBC.close();

        return M;
    }

    public Mensaje InsertarSubCategorias(Categorias C) throws SQLException {
        M = new Mensaje();

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_INSERTAR_SUBCATEGORIA (?,?,?,?); END;");

        JDBC.call.setString(1, C.getNombre());
        JDBC.call.setLong(2, C.getCategoria_Madre_Id());
        JDBC.call.registerOutParameter(3, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(4, OracleTypes.VARCHAR);

        JDBC.call.execute();

        M.setNumero(JDBC.call.getInt(3));
        M.setMensaje(JDBC.call.getString(4));

        JDBC.call.close();
        JDBC.close();

        return M;
    }

    public Mensaje ModificarPrincipales(Categorias C) throws SQLException {
        M = new Mensaje();

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_MODIFICAR_PRINCIPALES(?,?,?,?); END;");

        JDBC.call.setLong(1, C.getId_Categoria());
        JDBC.call.setString(2, C.getNombre());
        JDBC.call.registerOutParameter(3, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(4, OracleTypes.VARCHAR);

        JDBC.call.execute();

        M.setNumero(JDBC.call.getInt(3));
        M.setMensaje(JDBC.call.getString(4));

        return M;
    }

    public Mensaje ModificarCategoria(Categorias C) throws SQLException {
        M = new Mensaje();

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_MODIFICAR_CATEGORIA(?,?,?,?,?); END;");

        JDBC.call.setLong(1, C.getId_Categoria());
        JDBC.call.setString(2, C.getNombre());
        JDBC.call.setLong(3, C.getCategoria_Madre_Id());
        JDBC.call.registerOutParameter(4, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(5, OracleTypes.VARCHAR);

        JDBC.call.execute();

        M.setNumero(JDBC.call.getInt(4));
        M.setMensaje(JDBC.call.getString(5));

        return M;

    }

    public Mensaje EliminarCategoria(Long ID) throws SQLException {
        M = new Mensaje();

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_ELIMINAR_CATEGORIA (?,?,?); END;");

        JDBC.call.setLong(1, ID);
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
