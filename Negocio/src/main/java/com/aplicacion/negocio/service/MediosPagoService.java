package com.aplicacion.negocio.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aplicacion.negocio.controller.JDBCconnection;
import com.aplicacion.negocio.entity.MediosPago;
import com.aplicacion.negocio.entity.Mensaje;

import oracle.jdbc.OracleTypes;

@Service
public class MediosPagoService {

    JDBCconnection JDBC = new JDBCconnection();

    Mensaje M = new Mensaje();

    public List<MediosPago> ObtenerMPagos() throws SQLException {
        List<MediosPago> ArrayMedios = new ArrayList<>();

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_OBTENER_MEDIOS_PAGO (?,?,?); END;");

        JDBC.call.registerOutParameter(1, OracleTypes.REF_CURSOR);
        JDBC.call.registerOutParameter(2, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);

        JDBC.call.execute();

        ResultSet RS = (ResultSet) JDBC.call.getObject(1);

        while (RS.next()) {
            MediosPago MP = new MediosPago(
                    RS.getLong(1),
                    RS.getString(2));
            ArrayMedios.add(MP);
        }
        RS.close();
        JDBC.call.close();
        JDBC.close();

        return ArrayMedios;
    }

    public MediosPago ObtenerMedioPorID(Long id) throws SQLException {

        MediosPago MP;

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_OBTENER_UN_MEDIO_PAGO (?,?,?,?); END;");

        JDBC.call.setLong(1, id);
        JDBC.call.registerOutParameter(2, OracleTypes.VARCHAR);
        JDBC.call.registerOutParameter(3, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(4, OracleTypes.VARCHAR);

        JDBC.call.execute();

        String N = (String) JDBC.call.getObject(2);

        MP = new MediosPago(id, N);

        JDBC.call.close();
        JDBC.close();

        return MP;
    }

    public Mensaje InsertarMedioPago(MediosPago MP) throws SQLException {

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_INSERTAR_MEDIO_PAGO (?,?,?); END;");

        JDBC.call.setString(1, MP.getNombre());
        JDBC.call.registerOutParameter(2, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(3, OracleTypes.VARCHAR);

        JDBC.call.execute();

        M.setNumero(JDBC.call.getInt(2));
        M.setMensaje(JDBC.call.getString(3));

        JDBC.call.close();
        JDBC.close();

        return M;
    }

    public Mensaje ModificarMedioPago(MediosPago MP) throws SQLException {
        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_MODIFICAR_MEDIO_PAGO (?,?,?,?); END;");

        JDBC.call.setLong(1, MP.getMedio_Id());
        JDBC.call.setString(2, MP.getNombre());
        JDBC.call.registerOutParameter(3, OracleTypes.NUMBER);
        JDBC.call.registerOutParameter(4, OracleTypes.VARCHAR);

        JDBC.call.execute();

        M.setNumero(JDBC.call.getInt(3));
        M.setMensaje(JDBC.call.getString(4));

        return M;

    }

    public Mensaje EliminarMedioPago(Long Id) throws SQLException {

        JDBC.init();

        JDBC.prepareCall("BEGIN NEGOCIO.SP_ELIMINAR_MEDIO_PAGO (?,?,?); END;");

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
