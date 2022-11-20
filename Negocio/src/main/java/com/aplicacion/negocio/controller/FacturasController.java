/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.controller;

import com.aplicacion.negocio.entity.Facturas;
import com.aplicacion.negocio.service.Detalles_FacturaService;
import com.aplicacion.negocio.service.FacturasService;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author XPC
 */
@Controller
public class FacturasController {

    @Autowired
    FacturasService factService;

    @Autowired
    Detalles_FacturaService detallesService;

    @GetMapping("/listaFacturas")
    public String index(Model M) throws SQLException {
        List<Facturas> variable = factService.obtenerFacturasSinDetalle();
        M.addAttribute("lista", variable);
        return "Tmplt_listarFacturas";
    }
}
