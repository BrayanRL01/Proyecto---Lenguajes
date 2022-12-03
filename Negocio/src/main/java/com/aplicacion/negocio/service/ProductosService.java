package com.aplicacion.negocio.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.Mensaje;
import com.aplicacion.negocio.entity.Productos;

import oracle.jdbc.OracleTypes;

@Service
public class ProductosService {

    JDBCconnection DB = new JDBCconnection();

    Mensaje M = new Mensaje();

    public List<Productos> ObtenerProductos() throws SQLException {

        List<Productos> LP = new ArrayList<>();

        DB.init();

        DB.prepareCall("BEGIN NEGOCIO.SP_OBTENER_PRODUCTOS(?,?,?); END;");
        DB.call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        DB.call.registerOutParameter(2, OracleTypes.NUMBER);
        DB.call.registerOutParameter(3, OracleTypes.VARCHAR);

        DB.call.execute();

        ResultSet RS = (ResultSet) DB.call.getObject(1);
        String N = (String) DB.call.getObject(3);

        while (RS.next()) {
            Productos P = new Productos(
                    RS.getLong(1),
                    RS.getString(2),
                    RS.getString(3),
                    RS.getString(4),
                    RS.getString(5),
                    RS.getString(6),
                    RS.getLong(7),
                    RS.getString(8),
                    RS.getLong(9));
            LP.add(P);
        }

        //System.out.println(N);

        RS.close();
        DB.call.close();
        DB.close();

        return LP;
    }

    public Productos ObtenerProductosPorID(Long id) throws SQLException {
        Productos P = new Productos();

        DB.init();

        DB.prepareCall("BEGIN NEGOCIO.SP_OBTENER_UN_PRODUCTO(?,?,?,?); END;");

        DB.call.setLong(1, id);
        DB.call.registerOutParameter(2, OracleTypes.REF_CURSOR);
        DB.call.registerOutParameter(3, OracleTypes.NUMBER);
        DB.call.registerOutParameter(4, OracleTypes.VARCHAR);

        DB.call.execute();
        ResultSet RS = (ResultSet) DB.call.getObject(2);

        while (RS.next()) {
            P = new Productos(
                    RS.getLong(1),
                    RS.getString(2),
                    RS.getLong(3), 
                    RS.getLong(4), 
                    RS.getString(5),
                    RS.getString(6), 
                    RS.getLong(7),
                    RS.getString(8), 
                    RS.getLong(9));
        }
        System.out.println("Producto: "+P.getId_Producto());
        DB.call.close();
        DB.close();

        return P;
    }    

    public Mensaje InsertarProducto(Productos P) throws SQLException {
        DB.init();

        DB.prepareCall("BEGIN NEGOCIO.SP_INSERTAR_PRODUCTO (?,?,?,?,?,?,?,?,?,?); END;");

        DB.call.setString(1, P.getCodigo());
        DB.call.setLong(2, P.getId_Marca());
        DB.call.setLong(3, P.getId_Categoria());
        DB.call.setString(4, P.getNombre());
        DB.call.setString(5, P.getDetalle());
        DB.call.setLong(6, P.getPrecio());
        DB.call.setString(7, P.getTamano());
        DB.call.setLong(8, P.getCantidad());
        DB.call.registerOutParameter(9, OracleTypes.NUMBER);
        DB.call.registerOutParameter(10, OracleTypes.VARCHAR);

        DB.call.execute();

       M.setNumero(DB.call.getInt(9));
       M.setMensaje(DB.call.getString(10));

        DB.call.close();
        DB.close();

        return M;
    }

    public Mensaje ModificarProducto(Productos P) throws SQLException {
        DB.init();

        DB.prepareCall("BEGIN NEGOCIO.SP_MODIFICAR_PRODUCTO(?,?,?,?,?,?,?,?,?,?,?); END;");

        DB.call.setLong(1, P.getId_Producto());
        DB.call.setString(2, P.getCodigo());
        DB.call.setLong(3, P.getId_Marca());
        DB.call.setLong(4, P.getId_Categoria());
        DB.call.setString(5, P.getNombre());
        DB.call.setString(6, P.getDetalle());
        DB.call.setLong(7, P.getPrecio());
        DB.call.setString(8, P.getTamano());
        DB.call.setLong(9, P.getCantidad());
        DB.call.registerOutParameter(10, OracleTypes.NUMBER);
        DB.call.registerOutParameter(11, OracleTypes.VARCHAR);

        DB.call.execute();

        M.setNumero(DB.call.getInt(10));
        M.setMensaje(DB.call.getString(11));

        return M;
    }

    public Mensaje EliminarProducto(Long Id) throws SQLException {

        DB.init();

        DB.prepareCall("BEGIN NEGOCIO.SP_ELIMINAR_PRODUCTO(?,?,?); END;");

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
