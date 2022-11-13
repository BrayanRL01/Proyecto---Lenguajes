package com.aplicacion.negocio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.aplicacion.negocio.entity.Productos;
import com.aplicacion.negocio.service.ProductosService;

@Controller
public class ProductosController {

    @Autowired
    ProductosService PS;

    @GetMapping("/listaproductos")
    public String Index(Model M) {
        List<Productos> ListaProductos = PS.ListaProductos();
        M.addAttribute("TAB_PRODUCTOS", ListaProductos);
        return "listaproductos";
    }

}
