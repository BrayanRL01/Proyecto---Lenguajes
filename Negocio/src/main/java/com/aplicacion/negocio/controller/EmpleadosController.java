package com.aplicacion.negocio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.aplicacion.negocio.entity.Empleados;
import com.aplicacion.negocio.service.EmpleadosService;

@Controller
public class EmpleadosController {

    @Autowired
    EmpleadosService ES;

    @GetMapping("/listaempleados")
    public String Index (Model M) {
        List<Empleados> ListaEmpleados = ES.ListaEmpleados();
        M.addAttribute("EMPLOYEES", ListaEmpleados);
        return "listaempleados";
    }
}
