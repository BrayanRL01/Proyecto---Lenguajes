/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.controller;


import com.aplicacion.negocio.entity.Personas;
import com.aplicacion.negocio.service.PersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author XPC
 */
@RestController
public class PersonasController {
    @Autowired
    PersonaService personaService;
    
    @GetMapping("/personaLista")
    public ResponseEntity<List<Personas>> lista(){
        List<Personas> lista = personaService.lista();
        return new ResponseEntity(lista,HttpStatus.OK);
    } 
}
