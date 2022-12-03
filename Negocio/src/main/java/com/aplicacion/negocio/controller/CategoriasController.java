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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aplicacion.negocio.entity.Categorias;
import com.aplicacion.negocio.entity.Mensaje;
import com.aplicacion.negocio.service.CategoriasService;

@Controller
public class CategoriasController {

    @Autowired
    CategoriasService CS;

    Mensaje M = new Mensaje();

    // Lista de categorías y subcategorías
    @GetMapping("/listacategorias")
    public String Index(Model M) throws SQLException {
        List<Categorias> ListaCategorias = CS.ObtenerCategorias();
        M.addAttribute("titulo", "Categorías");
        M.addAttribute("TAB_CATEGORIAS", ListaCategorias);
        return "listacategorias";
    }

    @GetMapping("/listasubcategorias")
    public String IndexII(Model M) throws SQLException {
        List<Categorias> ListaSubCategorias = CS.ObtenerSubCategorias();
        M.addAttribute("titulo", "Subcategorías");
        M.addAttribute("TAB_SUBCATEGORIAS", ListaSubCategorias);
        return "listasubcategorias";
    }

    // Crear nuevas categorías y subcategorías.
    @GetMapping("/nuevacategoria")
    public String CrearCategoria(Model M) throws SQLException {
        M.addAttribute("accion", "añadiendo");
        M.addAttribute("prefijo", "a");
        M.addAttribute("titulo", "Categorías");
        M.addAttribute("Categorias", new Categorias());
        M.addAttribute("boton", "Añadir");
        return "nuevacategoria";
    }

    @GetMapping("/nuevasubcategoria")
    public String CrearSubCategoria(Model M) throws SQLException {
        List<Categorias> Listasubcategorias = CS.ObtenerCategorias();
        M.addAttribute("accion", "añadiendo");
        M.addAttribute("prefijo", "a");
        M.addAttribute("titulo", "Subcategorías");
        M.addAttribute("Categorias", new Categorias());
        M.addAttribute("Subcategoria", Listasubcategorias);
        M.addAttribute("boton", "Añadir");
        return "nuevasubcategoria";
    }

    // Guarda la nueva categoría o subcategoría
    @PostMapping("/GuardarCategoria")
    public String GuardarCategoria(@ModelAttribute Categorias C, RedirectAttributes N) throws SQLException {
        M = CS.InsertarCategorias(C);
        if (M.getNumero() == 1) {
            N.addFlashAttribute("error", "Hubo un error: " + M.getMensaje());
            return "redirect:/listacategorias";
        } else {
            N.addFlashAttribute("success", "Se añadió con éxito una nueva categoría.");
            return "redirect:/listacategorias";
        }

    }

    @PostMapping("/GuardarSubCategoria")
    public String GuardarSubCategoria(@ModelAttribute Categorias C, RedirectAttributes N) throws SQLException {
        M = CS.InsertarSubCategorias(C);
        if (M.getNumero() == 1) {
            N.addFlashAttribute("error", "Hubo un error: " + M.getMensaje());
            return "redirect:/listasubcategorias";
        } else {
            N.addFlashAttribute("success", "Se añadió con éxito una nueva subcategoría.");
            return "redirect:/listasubcategorias";
        }
    }

    @GetMapping("/ModificarCategoria/{Id_Categoria}")
    public String ModificarCategoria(@PathVariable("Id_Categoria") Long Id_Categoria, Model M) throws SQLException {
        Categorias Categoria = CS.ObtenerCategoriasPorID(Id_Categoria);
        M.addAttribute("accion", "editando");
        M.addAttribute("prefijo", "de");
        M.addAttribute("titulo", "Categorías");
        M.addAttribute("Categorias", Categoria);
        M.addAttribute("boton", "Actualizar");
        return "modificarcategoria";
    }

    @GetMapping("/ModificarSubCategoria/{Id_Categoria}")
    public String ModificarSubCategoria(@PathVariable("Id_Categoria") Long Id_Categoria, Model M) throws SQLException {
        Categorias Categoria = CS.ObtenerCategoriasPorID(Id_Categoria);
        List<Categorias> ListaCategorias = CS.ObtenerCategorias();
        M.addAttribute("accion", "editando");
        M.addAttribute("prefijo", "de");
        M.addAttribute("titulo", "Subcategorías");
        M.addAttribute("SubCategorias", Categoria);
        M.addAttribute("Categorias", ListaCategorias);
        M.addAttribute("boton", "Actualizar");
        return "modificarsubcategoria";
    }

    @PostMapping("/EditarCategoria")
    public String EditarCategoria(@ModelAttribute Categorias C, RedirectAttributes N) throws SQLException {
        M = CS.ModificarPrincipales(C);
        if (M.getNumero() == 0) {
            N.addFlashAttribute("success", "Se actualizó la categoría con éxito.");
            return "redirect:/listacategorias";
        } else {
            N.addFlashAttribute("error", "Hubo un error: " + M.getMensaje());
            return "redirect:/listacategorias";
        }
    }

    @PostMapping("/EditarSubCategoria")
    public String EditarSubCategoria(@ModelAttribute Categorias C, RedirectAttributes N)
            throws SQLException {
        M = CS.ModificarCategoria(C);
        if (M.getNumero() == 0) {
            N.addFlashAttribute("success", "Se actualizó la subcategoría con éxito.");
            return "redirect:/listasubcategorias";
        } else {
            N.addFlashAttribute("error", "Hubo un error: " + M.getMensaje());
            return "redirect:/listasubcategorias";
        }

    }

    @GetMapping("/EliminarCategoria/{Id_Categoria}")
    public String EliminarCategoria(@PathVariable("Id_Categoria") Long Id_Categoria, RedirectAttributes N)
            throws SQLException {
        M = CS.EliminarCategoria(Id_Categoria);
        if (M.getNumero() == 0) {
            N.addFlashAttribute("success", "Se eliminó la categoría con éxito.");
            return "redirect:/listacategorias";
        } else {
            N.addFlashAttribute("error", "Hubo un error: " + M.getMensaje());
            return "redirect:/listacategorias";
        }
    }

    @GetMapping("/EliminarSubCategoria/{Id_Categoria}")
    public String EliminarSubCategoria(@PathVariable("Id_Categoria") Long Id_Categoria, RedirectAttributes N)
            throws SQLException {
        M = CS.EliminarCategoria(Id_Categoria);
        if (M.getNumero() == 0) {
            N.addFlashAttribute("success", "Se eliminó la subcategoría con éxito.");
            return "redirect:/listasubcategorias";
        } else {
            N.addFlashAttribute("error", "Hubo un error: " + M.getMensaje());
            return "redirect:/listasubcategorias";
        }

    }
}