package com.aplicacion.negocio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.aplicacion.negocio.entity.Personas;
import com.aplicacion.negocio.service.PersonasService;

@Controller
public class PersonasController {

    @Autowired
    PersonasService PS;

    @GetMapping("/listapersonas")
    public String Index(Model M) {
        List<Personas> ListaPersonas = PS.ListaPersonas();
        M.addAttribute("TAB_PERSONAS", ListaPersonas);
        return "listapersonas";
    }
}
