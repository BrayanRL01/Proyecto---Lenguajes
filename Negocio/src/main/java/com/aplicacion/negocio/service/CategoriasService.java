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

        JDBC.prepareCall("BEGIN NEGOCIO.SP_OBTENER_CATEGORIAS_PRINCIPALES (?); END;");

        JDBC.call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        JDBC.call.execute();

        ResultSet RS = (ResultSet) JDBC.call.getObject(1);

        while (RS.next()) {
            Categorias C = new Categorias(
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

    public List<Categorias> ObtenerSubCategorias() throws SQLException {

        List<Categorias> LC = new ArrayList<>();

        JDBC.init();

        JDBC.prepareCall("BEGIN SP_OBTENER_SUBCATEGORIAS_EN_CATEGORIA (?,?,?); END;");

        JDBC.call.registerOutParameter(1, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        JDBC.call.registerOutParameter(1, OracleTypes.NUMBER);
        JDBC.call.execute();

        ResultSet RS = (ResultSet) JDBC.call.getObject(1);

        while (RS.next()) {
            Categorias C = new Categorias(
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
   
        Categorias C;

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_OBTENER_CATEGORIA_CON_ID (?,?,?); END;");

        JDBC.call.setLong(1, id);
        JDBC.call.registerOutParameter(2, OracleTypes.VARCHAR);
        JDBC.call.registerOutParameter(3, OracleTypes.NUMBER);

        JDBC.call.execute();

        String N = (String) JDBC.call.getObject(2);
        Long M = (Long) JDBC.call.getObject(3);
      
        C = new Categorias(id, N, M);
    
        JDBC.call.close();
        JDBC.close();

        return C;
    }


    public void InsertarCategorias(Categorias C) throws SQLException {
        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_INSERTAR_CATEGORIA (?,?,?); END;");

        JDBC.call.setString(1, C.getNombre());
        JDBC.call.setLong(2, C.getCategoria_Madre_Id());
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);    
 
        JDBC.call.execute();

        JDBC.call.close();
        JDBC.close();
    }

    // public void eliminarPersona(Long per) throws SQLException {

    //     jdbc.init();

    //     // Prepare a PL/SQL call
    //     jdbc.prepareCall("BEGIN NEGOCIO.SP_ELIMINAR_PERSONA (?,?); END;");

    //     jdbc.call.setLong(1, per);
    //     jdbc.call.registerOutParameter(2, OracleTypes.NUMBER);

    //     // se ejecuta el query
    //     jdbc.call.setQueryTimeout(10);
    //     jdbc.call.execute();

    //     // System.out.println("+++++++++++++++++ Resultado de SP_ELIMINAR_PERSONA: " +
    //     // rset);
    // }

}
