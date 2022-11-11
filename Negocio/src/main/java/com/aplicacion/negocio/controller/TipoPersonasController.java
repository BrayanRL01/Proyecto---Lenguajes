/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.controller;

import com.aplicacion.negocio.entity.Tipo_Personas;
import com.aplicacion.negocio.service.TipoPersonasService;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;




/**
 *
 * @author XPC
 */
@RestController
public class TipoPersonasController {
    
    @Autowired
    TipoPersonasService tpService;

    @GetMapping("/tpPersonaLista")
    public String Index (Model M) throws SQLException {
        //List<Tipo_Personas> tpLista = tpService.lista();
        String variable = tpService.lista();
        //System.out.println ("variableeeeeeeeeeeee: "+variable);
        M.addAttribute("lista", variable);
        return variable;
    }
}
