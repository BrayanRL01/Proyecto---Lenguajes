package com.aplicacion.negocio.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.aplicacion.negocio.entity.Productos;
import com.aplicacion.negocio.service.CategoriasService;
import com.aplicacion.negocio.service.MarcasService;
import com.aplicacion.negocio.service.ProductosService;

@Controller
public class ProductosController {
    @Autowired
    ProductosService PS;

    @Autowired
    CategoriasService CS;

    @Autowired 
    MarcasService MS;

    @GetMapping("/listaproductos")
    public String Index(Model M) throws SQLException {
        List<Productos> ListaProductos = PS.ObtenerProductos();
        M.addAttribute("Productos", ListaProductos);
        return "listaproductos";
    }


}
