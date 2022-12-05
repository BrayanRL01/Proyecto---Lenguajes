package com.aplicacion.negocio.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.Mensaje;
import com.aplicacion.negocio.entity.TiposVenta;

import oracle.jdbc.OracleTypes;

@Service
public class TipoVentaService {

    JDBCconnection JDBC = new JDBCconnection();

    Mensaje M = new Mensaje();

    public List<TiposVenta> ObtenerVentas() throws SQLException {
        List<TiposVenta> ArrayVentas = new ArrayList<>();

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_OBTENER_TIPOS_VENTA (?,?,?); END;");

        JDBC.call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        JDBC.call.registerOutParameter(2, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);

        JDBC.call.execute();

        ResultSet RS = (ResultSet) JDBC.call.getObject(1);

        while (RS.next()) {
            TiposVenta TV = new TiposVenta(
                    RS.getLong(1),
                    RS.getString(2));
            ArrayVentas.add(TV);
        }
        RS.close();
        JDBC.call.close();
        JDBC.close();

        return ArrayVentas;
    }

    public TiposVenta ObtenerVentaPorID(Long id) throws SQLException {

        TiposVenta TV;
        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_OBTENER_UN_TIPO_VENTA (?,?,?,?); END;");

        JDBC.call.setLong(1, id);
        JDBC.call.registerOutParameter(2, OracleTypes.VARCHAR);
        JDBC.call.registerOutParameter(3, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(4, OracleTypes.VARCHAR);

        JDBC.call.execute();

        String N = (String) JDBC.call.getObject(2);

        TV = new TiposVenta(id, N);

        JDBC.call.close();
        JDBC.close();

        return TV;
    }

    public Mensaje InsertarTipoVenta(TiposVenta TV) throws SQLException {

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_INSERTAR_TIPO_VENTA (?,?,?); END;");

        JDBC.call.setString(1, TV.getNombre());
        JDBC.call.registerOutParameter(2, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);

        JDBC.call.execute();

        M.setNumero(JDBC.call.getInt(2));
        M.setMensaje(JDBC.call.getString(3));

        JDBC.call.close();
        JDBC.close();

        return M;
    }

    public Mensaje ModificarTipoVenta(TiposVenta TV) throws SQLException {
        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_MODIFICAR_TIPO_VENTA (?,?,?,?); END;");

        JDBC.call.setLong(1, TV.getVenta_Id());
        JDBC.call.setString(2, TV.getNombre());
        JDBC.call.registerOutParameter(3, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(4, OracleTypes.VARCHAR);

        JDBC.call.execute();

        M.setNumero(JDBC.call.getInt(3));
        M.setMensaje(JDBC.call.getString(4));

        return M;
    }

    public Mensaje EliminarTipoVenta(Long Id) throws SQLException {

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_ELIMINAR_TIPO_VENTA (?,?,?); END;");

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
