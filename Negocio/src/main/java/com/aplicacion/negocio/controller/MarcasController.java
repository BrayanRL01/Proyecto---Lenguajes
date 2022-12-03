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

import com.aplicacion.negocio.entity.Marcas;
import com.aplicacion.negocio.entity.Mensaje;
import com.aplicacion.negocio.service.MarcasService;

@Controller
public class MarcasController {

    @Autowired
    MarcasService MS;

    Mensaje N = new Mensaje();

    @GetMapping("/listamarcas")
    public String Index(Model M) throws SQLException {
        List<Marcas> ListaMarcas = MS.ObtenerMarcas();
        M.addAttribute("titulo", "Marcas");
        M.addAttribute("TAB_MARCAS", ListaMarcas);
        return "listamarcas";
    }

    @GetMapping("/nuevamarca")
    public String CrearMarca(Model M) throws SQLException {
        M.addAttribute("titulo", "Marcas");
        M.addAttribute("accion", "añadiendo");
        M.addAttribute("prefijo", "a");
        M.addAttribute("Marcas", new Marcas());
        M.addAttribute("boton", "Añadir");
        return "nuevamarca";
    }

    @PostMapping("/GuardarMarca")
    public String GuardarMarca(@ModelAttribute Marcas M, RedirectAttributes flash) throws SQLException {
        N = MS.InsertarMarcas(M);
        if (N.getNumero() == 0) {
            flash.addFlashAttribute("mensaje", "Marca creada con éxito.");
            return "redirect:/listamarcas";
        } else {
            flash.addFlashAttribute("error", "Hubo un error: " + N.getMensaje());
            return "redirect:/listamarcas";
        }
    }

    @GetMapping("/ModificarMarca/{Id_Marca}")
    public String EditarMarca(@PathVariable("Id_Marca") Long Id_Marca, Model M) throws SQLException {
        Marcas Marca = MS.ObtenerMarcaPorID(Id_Marca);
        M.addAttribute("accion", "editando");
        M.addAttribute("prefijo", "de");
        M.addAttribute("titulo", "Marcas");
        M.addAttribute("Marcas", Marca);
        M.addAttribute("boton", "Actualizar");
        return "modificarmarca";
    }

    @PostMapping("/EditarMarca")
    public String EditarMarca(@ModelAttribute Marcas M, RedirectAttributes flash) throws SQLException {
        N = MS.ModificarMarca(M);
        if (N.getNumero() == 0) {
            flash.addFlashAttribute("mensaje", "Marca actualizada con éxito.");
            return "redirect:/listamarcas";
        } else {
            flash.addFlashAttribute("error", "Hubo un error: " + N.getMensaje());
            return "redirect:/listamarcas";
        }
    }

    @GetMapping("/EliminarMarca/{Id_Marca}")
    public String EliminarMarca(@PathVariable("Id_Marca") Long Id_Marca, RedirectAttributes flash) throws SQLException {
        N = MS.EliminarMarca(Id_Marca);
        if (N.getNumero() == 0) {
            flash.addFlashAttribute("mensaje", "Marca eliminada con éxito.");
            return "redirect:/listamarcas";
        } else {
            flash.addFlashAttribute("error", "Hubo un error: " + N.getMensaje());
            return "redirect:/listamarcas";
        }
    }

}
