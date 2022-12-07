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

import com.aplicacion.negocio.entity.Mensaje;
import com.aplicacion.negocio.entity.Roles;
import com.aplicacion.negocio.service.RolesService;

@Controller
public class RolesController {

    @Autowired
    RolesService RS;

    Mensaje M;

    @GetMapping("/listaroles")
    public String Index(Model M) throws SQLException {
        List<Roles> ListaRoles = RS.ObtenerRoles();
        M.addAttribute("titulo", "Roles");
        M.addAttribute("Roles", ListaRoles);
        return "listaroles";
    }

    @GetMapping("/nuevorol")
    public String CrearRol(Model M) throws SQLException {
        M.addAttribute("accion", "añadiendo");
        M.addAttribute("prefijo", "a");
        M.addAttribute("titulo", "Roles");
        M.addAttribute("Rol", new Roles());
        M.addAttribute("boton", "Añadir");
        return "nuevorol";
    }

    @PostMapping("/GuardarRol")
    public String GuardarRol(@ModelAttribute Roles R, RedirectAttributes flash) throws SQLException {
        M = RS.InsertarRoles(R);
        if (M.getNumero() == 0) {
            flash.addFlashAttribute("mensaje", "Rol creado con éxito.");
            return "redirect:/listaroles";
        } else {
            flash.addFlashAttribute("error", "Hubo un error: " + M.getMensaje());
            return "redirect:/listaroles";
        }

    }

    @GetMapping("/ModificarRol/{Rol_Id}")
    public String ModificarRol(@PathVariable("Rol_Id") Long Rol_Id, Model M)
            throws SQLException {
        Roles R = RS.ObtenerRolPorID(Rol_Id);
        M.addAttribute("accion", "editando");
        M.addAttribute("prefijo", "de");
        M.addAttribute("titulo", "Roles");
        M.addAttribute("Rol", R);
        M.addAttribute("boton", "Actualizar");
        return "modificarrol";
    }

    @PostMapping("/EditarRol")
    public String EditarRol(@ModelAttribute Roles R, RedirectAttributes flash) throws SQLException {
        M = RS.ModificarRol(R);
        if (M.getNumero() == 0) {
            flash.addFlashAttribute("mensaje", "Rol actualizado con éxito.");
            return "redirect:/listaroles";
        } else {
            flash.addFlashAttribute("error", "Hubo un error: " + M.getMensaje());
            return "redirect:/listaroles";
        }
    }

    @GetMapping("/EliminarRol/{Rol_Id}")
    public String EliminarRol(@PathVariable("Rol_Id") Long Rol_Id, RedirectAttributes flash)
            throws SQLException {
        M = RS.EliminarRol(Rol_Id);
        if (M.getNumero() == 0) {
            flash.addFlashAttribute("mensaje", "Rol eliminado con éxito.");
            return "redirect:/listaroles";
        } else {
            flash.addFlashAttribute("error", "Hubo un error: " + M.getMensaje());
            return "redirect:/listaroles";
        }
    }
}
