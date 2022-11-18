package com.aplicacion.negocio.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCconnection {

    private Connection conn;
    public CallableStatement call;

    public void init() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "WEB_ACCESS",
                "ExternalWeb22");
    }

    public void prepareCall(String sqlQuery) throws SQLException {
        call = conn.prepareCall(sqlQuery);
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void close() throws SQLException {
        this.conn.close();
    }

    public CallableStatement getCall() {
        return call;
    }

    public void setCall(CallableStatement call) {
        this.call = call;
    }

    public void execute() throws SQLException {
        this.call.execute();
    }
}
