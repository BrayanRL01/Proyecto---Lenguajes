/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.controller;

import com.aplicacion.negocio.entity.Tipo_Personas;
import com.aplicacion.negocio.service.TipoPersonasService;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.hibernate.procedure.ProcedureOutputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.io.*;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.*;

/**
 *
 * @author XPC
 */
@RestController
public class TipoPersonasController {

    @Autowired
    TipoPersonasService tpService;

    @GetMapping("/tpPersonaLista")
    public /*ResponseEntity<List<Tipo_Personas>>*/ void lista() throws SQLException {
        /*
        List<Tipo_Personas> lista = tpService.lista();
        return new ResponseEntity(lista,HttpStatus.OK);*/

       // Load the driver
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

    // Connect to the database
    // You can put a database name after the @ sign in the connection URL.
    Connection conn =
      DriverManager.getConnection ("jdbc:oracle:thin:@//localhost:1521/ORCLPDB", "WEB_ACCESS", "ExternalWeb22");

    // Create the stored procedure
    init (conn);

    // Prepare a PL/SQL call
    CallableStatement call =
      conn.prepareCall ("{ ? = call java_refcursor.SP_GET_TIPO_PERSONAS (?)}");

    // Find out all the SALES person
    call.registerOutParameter (2, OracleTypes.CURSOR);
    call.setInt(1, 1);
    call.execute ();
    ResultSet rset = (ResultSet)call.getObject (2);

    // Dump the cursor
    while (rset.next ())
      System.out.println (rset.getString ("ENAME"));

    // Close all the resources
    rset.close();
    call.close();
    conn.close();
}
    
    // Utility function to create the stored procedure
  static void init (Connection conn)
       throws SQLException
  {
    Statement stmt = conn.createStatement ();

    stmt.execute ("create or replace package java_refcursor as " +
                  "  type myrctype is ref cursor return EMP%ROWTYPE; " +
                  "  function job_listing (j varchar2) return myrctype; " +
                  "end java_refcursor;");

    stmt.execute ("create or replace package body java_refcursor as " +
              "  function job_listing (j varchar2) return myrctype is " +
              "    rc myrctype; " +
              "  begin " +
              "    open rc for select * from emp where job = j; " +
              "    return rc; " +
              "  end; " +
              "end java_refcursor;");
    stmt.close();
  }
}