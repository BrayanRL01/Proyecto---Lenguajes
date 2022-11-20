package com.aplicacion.negocio.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.Categorias;
import com.aplicacion.negocio.entity.Productos;
import com.aplicacion.negocio.repository.CategoriasRepository;

import oracle.jdbc.internal.OracleTypes;

@Service
public class CategoriasService {

    CategoriasRepository CR;

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

    // public void GuardarCategoria(Categorias C) throws SQLException {
   
    //     JDBC.init();

    //     // Prepare a PL/SQL call
    //     JDBC.prepareCall("BEGIN NEGOCIO.SP_INSERTAR_CATEGORIA (?,?,?); END;");

    //     JDBC.call.execute();

    //     JDBC.call.close();
    //     JDBC.close();
    // }

    // public Personas getPersonaPorID(Long id) throws SQLException {
    //     Personas per = new Personas();

    //     jdbc.init();

    //     // Prepare a PL/SQL call
    //     jdbc.prepareCall("BEGIN NEGOCIO.SP_OBTENER_UNA_PERSONA (?,?); END;");

    //     jdbc.call.setLong(1, id);
    //     jdbc.call.registerOutParameter(2, OracleTypes.REF_CURSOR);

    //     // se ejecuta el query
    //     jdbc.call.execute();

    //     // se almacena el resultado del query en rset
    //     ResultSet rset = (ResultSet) jdbc.call.getObject(2);

    //     while (rset.next()) {
    //         String nombre = rset.getString("NOMBRE");
    //         Long cedula = rset.getLong("CEDULA");
    //         String apellido1 = rset.getString("PRIMER_APELLIDO");
    //         String apellido2 = rset.getString("SEGUNDO_APELLIDO");
    //         String direccion = rset.getString("DIRECCION");
    //         String mail = rset.getString("EMAIL");
    //         String telefono = rset.getString("TELEFONO");
    //         String tipoP = rset.getString("TIPO_PERSONA");

    //         String var = ("Cedula: " + cedula + ", "
    //                 + "Nombre: " + nombre + ", "
    //                 + "apellido1: " + apellido1 + ", "
    //                 + "apellido2: " + apellido2 + ", "
    //                 + "direccion: " + direccion + ", "
    //                 + "Email: " + mail + ", "
    //                 + "telefono: " + telefono + ", "
    //                 + "tipoP: " + tipoP + "\n");

    //         per = new Personas(
    //                 rset.getLong(1),
    //                 rset.getLong(2),
    //                 rset.getString(3),
    //                 rset.getString(4),
    //                 rset.getString(5),
    //                 rset.getString(6),
    //                 rset.getString(7),
    //                 rset.getString(8),
    //                 1,
    //                 rset.getString(9));

    //     }
    //     jdbc.call.close();
    //     jdbc.close();

    //     return per;
    // }

    // public void actualizarPersona(Personas per) throws SQLException {
    //     jdbc.init();

    //     // Prepare a PL/SQL call
    //     jdbc.prepareCall("BEGIN NEGOCIO.SP_MODIFICAR_PERSONA (?,?,?,?,?,?,?,?,?,?); END;");

    //     /*
    //      * IN_ID_PERSONA IN NUMBER,
    //      * IN_CEDULA IN NUMBER,
    //      * IN_NOMBRE IN VARCHAR2,
    //      * IN_PRIMER_APELLIDO IN VARCHAR2,
    //      * IN_SEGUNDO_APELLIDO IN VARCHAR2,
    //      * IN_DIRECCION IN VARCHAR2,
    //      * IN_EMAIL VARCHAR2,
    //      * IN_TELEFONO IN VARCHAR2,
    //      * IN_TIPO_PERSONA_ID IN NUMBER,
    //      * RESULTADO OUT NUMBER
    //      */
    //     jdbc.call.setLong(1, per.getId_persona());
    //     jdbc.call.setLong(2, per.getCedula());
    //     jdbc.call.setString(3, per.getNombre());
    //     jdbc.call.setString(4, per.getPrimerAp());
    //     jdbc.call.setString(5, per.getSegundoAp());
    //     jdbc.call.setString(6, per.getDireccion());
    //     jdbc.call.setString(7, per.getEmail());
    //     jdbc.call.setString(8, per.getTelefono());
    //     jdbc.call.setInt(9, per.getTipoPersonaId());
    //     jdbc.call.registerOutParameter(10, OracleTypes.NUMBER);

    //     // se ejecuta el query
    //     jdbc.call.execute();

    //     // Integer rset = (int) jdbc.call.getObject(10);
    //     BigDecimal rset = (BigDecimal) jdbc.call.getObject(10);

    //     System.out.println("+++++++++++++++++ Resultado de SP_MODIFICAR_PERSONA: " + rset);

    // }

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
