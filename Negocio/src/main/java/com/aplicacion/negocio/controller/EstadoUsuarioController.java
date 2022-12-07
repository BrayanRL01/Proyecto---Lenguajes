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

import com.aplicacion.negocio.entity.EstadoUsuario;
import com.aplicacion.negocio.entity.Mensaje;
import com.aplicacion.negocio.service.EstadoUsuarioService;

@Controller
public class EstadoUsuarioController {

    @Autowired
    EstadoUsuarioService EUS;

    Mensaje M;

    @GetMapping("/listaestados")
    public String Index(Model M) throws SQLException {
        List<EstadoUsuario> ListaEstados = EUS.ObtenerEstados();
        M.addAttribute("titulo", "Estados");
        M.addAttribute("Estados", ListaEstados);
        return "listaestados";
    }

    @GetMapping("/nuevoestado")
    public String CrearEstado(Model M) throws SQLException {
        M.addAttribute("accion", "añadiendo");
        M.addAttribute("prefijo", "a");
        M.addAttribute("titulo", "Estados");
        M.addAttribute("Estados", new EstadoUsuario());
        M.addAttribute("boton", "Añadir");
        return "nuevoestado";
    }

    @PostMapping("/GuardarEstado")
    public String GuardarEstado(@ModelAttribute EstadoUsuario EU, RedirectAttributes flash) throws SQLException {
        M = EUS.InsertarEstados(EU);
        if (M.getNumero() == 0) {
            flash.addFlashAttribute("mensaje", "Estado de usuario creado con éxito.");
            return "redirect:/listaestados";
        } else {
            flash.addFlashAttribute("error", "Hubo un error: " + M.getMensaje());
            return "redirect:/listaestados";
        }
    }

    @GetMapping("/ModificarEstado/{Estado_Usuario_Id}")
    public String ModificarrEstado(@PathVariable("Estado_Usuario_Id") Long Estado_Usuario_Id, Model M)
            throws SQLException {
        EstadoUsuario EU = EUS.ObtenerEstadoPorID(Estado_Usuario_Id);
        M.addAttribute("accion", "editando");
        M.addAttribute("prefijo", "de");
        M.addAttribute("titulo", "Estados");
        M.addAttribute("Estado", EU);
        M.addAttribute("boton", "Actualizar");
        return "modificarestado";
    }

    @PostMapping("/EditarEstado")
    public String EditarEstado(@ModelAttribute EstadoUsuario EU, RedirectAttributes flash) throws SQLException {
        M = EUS.ModificarEstado(EU);
        if (M.getNumero() == 0) {
            flash.addFlashAttribute("mensaje", "Estado de usuario actualizado con éxito.");
            return "redirect:/listaestados";
        } else {
            flash.addFlashAttribute("error", "Hubo un error: " + M.getMensaje());
            return "redirect:/listaestados";
        }
    }

    @GetMapping("/EliminarEstado/{Estado_Usuario_Id}")
    public String EliminarMarca(@PathVariable("Estado_Usuario_Id") Long Estado_Usuario_Id, RedirectAttributes flash)
            throws SQLException {
        M = EUS.EliminarEstado(Estado_Usuario_Id);
        if (M.getNumero() == 0) {
            flash.addFlashAttribute("mensaje", "Estado de usuario eliminado con éxito.");
            return "redirect:/listaestados";
        } else {
            flash.addFlashAttribute("error", "Hubo un error: " + M.getMensaje());
            return "redirect:/listaestados";
        }
    }
}
