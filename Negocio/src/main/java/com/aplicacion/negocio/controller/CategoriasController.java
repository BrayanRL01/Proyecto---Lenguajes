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
import com.aplicacion.negocio.service.CategoriasService;

@Controller
public class CategoriasController {

    @Autowired
    CategoriasService CS;

// Lista de categorías y subcategorías
    @GetMapping("/listacategorias")
    public String Index(Model M) throws SQLException {
        List<Categorias> ListaCategorias = CS.ObtenerCategorias();
        M.addAttribute("TAB_CATEGORIAS", ListaCategorias);
        return "listacategorias";
    }

    @GetMapping("/listasubcategorias")
    public String IndexII(Model M) throws SQLException {
        List<Categorias> ListaSubCategorias = CS.ObtenerSubCategorias();
        M.addAttribute("TAB_SUBCATEGORIAS", ListaSubCategorias);
        return "listasubcategorias";
    }

// Crear nuevas categorías y subcategorías.
    @GetMapping("/nuevacategoria")
    public String CrearCategoria(Model M) throws SQLException {
        M.addAttribute("titulo", "Crear Categoria");
        M.addAttribute("Categorias", new Categorias());
        M.addAttribute("boton", "Crear");
        return "nuevacategoria";
    }

    @GetMapping("/nuevasubcategoria")
    public String CrearSubCategoria(Model M) throws SQLException {
        M.addAttribute("titulo", "Crear Categoria");
        M.addAttribute("Categorias", new Categorias());
        M.addAttribute("boton", "Crear");
        return "nuevasubcategoria";
    }

    @PostMapping("/GuardarCategoria")
    public String GuardarCategoria(@ModelAttribute Categorias C) throws SQLException {
        CS.InsertarCategorias(C);
        return "redirect:/listacategorias";
    }

    @PostMapping("/GuardarSubCategoria")
    public String GuardarSubCategoria(@ModelAttribute Categorias C) throws SQLException {
        CS.InsertarSubCategorias(C);
        return "redirect:/listasubcategorias";
    }

    @GetMapping("/ModificarCategoria/{Id_Categoria}")
    public String ModificarCategoria(@PathVariable("Id_Categoria") Long Id_Categoria, Model M) throws SQLException {
        Categorias Categoria = CS.ObtenerCategoriasPorID(Id_Categoria);
        M.addAttribute("titulo", "Editar Categoría");
        M.addAttribute("Categorias", Categoria);
        M.addAttribute("boton", "Actualizar");
        return "modificarcategoria";
    }

    @GetMapping("/ModificarSubCategoria/{Id_Categoria}")
    public String ModificarSubCategoria(@PathVariable("Id_Categoria") Long Id_Categoria, Model M) throws SQLException {
        Categorias Categoria = CS.ObtenerCategoriasPorID(Id_Categoria);
        M.addAttribute("titulo", "Editar Subcategoría");
        M.addAttribute("SubCategorias", Categoria);
        M.addAttribute("boton", "Actualizar");
        return "modificarsubcategoria";
    }

    @PostMapping("/EditarCategoria")
    public String EditarCategoria(@ModelAttribute Categorias C) throws SQLException {
        CS.ModificarCategoria(C);
        return "redirect:/listacategorias";
    }

    @PostMapping("/EditarSubCategoria")
    public String EditarSubCategoria(@ModelAttribute Categorias C) throws SQLException {
        CS.ModificarCategoria(C);
        return "redirect:/listasubcategorias";
    }

    @GetMapping("/EliminarCategoria/{Id_Categoria}")
    public String EliminarMarca(@PathVariable("Id_Categoria") Long Id_Categoria) throws SQLException {
        CS.EliminarCategoria(Id_Categoria);
        return "redirect:/listacategorias";

    }
}
