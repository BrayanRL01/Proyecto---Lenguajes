package com.aplicacion.negocio.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.aplicacion.negocio.entity.Categorias;
import com.aplicacion.negocio.entity.Marcas;
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
        M.addAttribute("titulo", "Productos");
        M.addAttribute("Productos", ListaProductos);
        return "listaproductos";
    }

    @GetMapping("/nuevoproducto")
    public String CrearProducto(Model M) throws SQLException {
        List<Marcas> ListaMarcas = MS.ObtenerMarcas();
        List<Categorias> ListaCategorias = CS.ObtenerSubCategorias();
        M.addAttribute("titulo", "Productos");
        M.addAttribute("accion", "añadiendo");
        M.addAttribute("prefijo", "a");
        M.addAttribute("Producto", new Productos());
        M.addAttribute("Marca", ListaMarcas);
        M.addAttribute("Subcategoria", ListaCategorias);
        M.addAttribute("boton", "Añadir");
        return "nuevoproducto";
    }

    @PostMapping("/GuardarProducto")
    public String GuardarProducto(@ModelAttribute Productos P) throws SQLException {
        PS.InsertarProducto(P);
        return "redirect:/listaproductos";
    }

    @GetMapping("/ModificarProducto/{Id_Producto}")
    public String ModificarCategoria(@PathVariable("Id_Producto") Long id_Producto, Model M) throws SQLException {
        Productos Producto = PS.ObtenerProductosPorID(id_Producto);
        List<Marcas> ListaMarcas = MS.ObtenerMarcas();
        List<Categorias> ListaCategorias = CS.ObtenerSubCategorias();
        M.addAttribute("accion", "editando");
        M.addAttribute("prefijo", "de");
        M.addAttribute("titulo", "Productos");
        M.addAttribute("Producto", Producto);
        M.addAttribute("Marca", ListaMarcas);
        M.addAttribute("Subcategoria", ListaCategorias);
        M.addAttribute("boton", "Actualizar");
        return "modificarproducto";
    }

    @PostMapping("/EditarProducto")
    public String EditarProducto(@ModelAttribute Productos P) throws SQLException {
        PS.ModificarProducto(P);
        return "redirect:/listaproductos";
    }

    @GetMapping("/EliminarProducto/{Id_Producto}")
    public String EliminarMarca(@PathVariable("Id_Producto") Long Id_Marca) throws SQLException {
        PS.EliminarProducto(Id_Marca);
        return "redirect:/listaproductos";
    }



}
