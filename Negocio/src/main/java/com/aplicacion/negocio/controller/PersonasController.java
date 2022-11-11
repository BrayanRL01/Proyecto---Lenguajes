package com.aplicacion.negocio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplicacion.negocio.entity.Personas;
import com.aplicacion.negocio.service.PersonasService;

@RestController
public class PersonasController {

    @Autowired
    PersonasService PS;

    @GetMapping("/listapersonas")
    public ResponseEntity<List<Personas>> ListaPersonas() {
        List<Personas> ListaPersonas = PS.ListaPersonas();
        return new ResponseEntity(ListaPersonas, HttpStatus.OK);
    }
}
