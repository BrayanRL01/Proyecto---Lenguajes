/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.controller;

// import com.aplicacion.negocio.entity.DetalleVista;
import com.aplicacion.negocio.entity.FacturaVista;
import com.aplicacion.negocio.entity.FacturasConDetalles;
import com.aplicacion.negocio.service.Detalles_FacturaService;
import com.aplicacion.negocio.service.FacturasService;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    public String listaFacturas(Model M) throws SQLException {
        List<FacturaVista> variable = factService.obtenerFacturasSinDetalle();
        if (variable.isEmpty()) {
            M.addAttribute("lista", variable);
            return "redirect:/personaLista";
        } else {
            return "Tmplt_listarFacturas";
        }
    }

    @GetMapping("/verFactura/{id}")
    public String listarDetalles(Model M, @PathVariable("id") long id_factura) throws SQLException, ClassNotFoundException {
        FacturasConDetalles FacturasConDetalles = factService.obtenerFactconDetalles(id_factura);
        M.addAttribute("factura", FacturasConDetalles);
        M.addAttribute("detalles", FacturasConDetalles.getListaDetalles());
        return "Tmplt_viewFactYDetalles";
    }

    @GetMapping("/facturaN")
    public String CrearFactura(Model model) throws SQLException, ClassNotFoundException {
        
        model.addAttribute("titulo", "Crear Factura");
        factService.crearFactura();
        return "Tmplt_listarFacturas";
    }

}
