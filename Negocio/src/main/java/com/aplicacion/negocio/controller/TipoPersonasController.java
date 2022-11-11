/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.controller;

import com.aplicacion.negocio.entity.Tipo_Personas;
import com.aplicacion.negocio.service.TipoPersonasService;
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
public class TipoPersonasController {
    @Autowired
    TipoPersonasService tpService;

    @GetMapping("/tpPersonaLista")
    public ResponseEntity<List<Tipo_Personas>> lista() {
        List<Tipo_Personas> lista = tpService.lista();
        return new ResponseEntity(lista, HttpStatus.OK);
    }
}
