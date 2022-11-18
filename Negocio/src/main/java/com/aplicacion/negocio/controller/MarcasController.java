package com.aplicacion.negocio.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.aplicacion.negocio.entity.Marcas;
import com.aplicacion.negocio.service.MarcasService;

@Controller
public class MarcasController {

    @Autowired
    MarcasService MS;

    @GetMapping("/listamarcas")
    public String Index(Model M) throws SQLException {
        List<Marcas> ListaMarcas = MS.ObtenerMarcas();
        M.addAttribute("TAB_MARCAS", ListaMarcas);
        return "listamarcas";
    }

}
