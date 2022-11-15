/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.controller;


import com.aplicacion.negocio.entity.Personas;
import com.aplicacion.negocio.service.PersonaService;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author XPC
 */
@Controller
public class PersonasController {
    @Autowired
    PersonaService personaService;
    
    @GetMapping("/personaLista")
    public String index(Model M) throws SQLException {
       //List<Tipo_Personas> tpLista = tpService.lista();
        List<Personas> variable = personaService.lista();
        //System.out.println ("variableeeeeeeeeeeee: "+variable);
        M.addAttribute("lista", variable);
        return "Tmplt_pLista";
    } 
}
