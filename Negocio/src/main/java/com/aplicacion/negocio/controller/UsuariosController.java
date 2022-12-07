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
import com.aplicacion.negocio.entity.Roles;
import com.aplicacion.negocio.entity.Usuarios;
import com.aplicacion.negocio.service.EstadoUsuarioService;
import com.aplicacion.negocio.service.RolesService;
import com.aplicacion.negocio.service.UsuariosService;

@Controller
public class UsuariosController {

    @Autowired
    UsuariosService US;

    @Autowired
    RolesService RS;

    @Autowired
    EstadoUsuarioService EUS;

    Mensaje M;

    @GetMapping("/listausuarios")
    public String Index(Model M) throws SQLException {
        List<Usuarios> ListaUsuarios = US.ObtenerUsuarios();
        M.addAttribute("titulo", "Usuarios");
        M.addAttribute("Usuarios", ListaUsuarios);
        return "listausuarios";
    }

    @GetMapping("/nuevousuario")
    public String CrearUsuario(Model M) throws SQLException {
        List<Roles> ListaRoles = RS.ObtenerRoles();
        List<EstadoUsuario> ListaEstados = EUS.ObtenerEstados();
        M.addAttribute("titulo", "Usuarios");
        M.addAttribute("accion", "añadiendo");
        M.addAttribute("prefijo", "a");
        M.addAttribute("Usuario", new Usuarios());
        M.addAttribute("Rol", ListaRoles);
        M.addAttribute("Estado", ListaEstados);
        M.addAttribute("boton", "Añadir");
        return "nuevousuario";
    }

    @PostMapping("/GuardarUsuario")
    public String GuardarUsuario(@ModelAttribute Usuarios U, RedirectAttributes N) throws SQLException {
        M = US.InsertarUsuario(U);
        if (M.getNumero() == 0) {
            N.addFlashAttribute("mensaje", "Se agregó un usuario con éxito.");
            return "redirect:/listausuarios";
        } else {
            N.addFlashAttribute("error", "Hubo un error: " + M.getMensaje());
            return "redirect:/listausuarios";
        }

    }

    @GetMapping("/ModificarUsuario/{Usuario_Id}")
    public String ModificarUsuario(@PathVariable("Usuario_Id") Long Id, Model M) throws SQLException {
        Usuarios U = US.ObtenerUsuarioPorID(Id);
        List<Roles> ListaRoles = RS.ObtenerRoles();
        List<EstadoUsuario> ListaEstados = EUS.ObtenerEstados();
        M.addAttribute("accion", "editando");
        M.addAttribute("prefijo", "de");
        M.addAttribute("titulo", "Usuarios");
        M.addAttribute("Usuario", U);
        M.addAttribute("Rol", ListaRoles);
        M.addAttribute("Estado", ListaEstados);
        M.addAttribute("boton", "Actualizar");
        return "modificarusuario";
    }

    @PostMapping("/EditarUsuario")
    public String EditarUsuario(@ModelAttribute Usuarios U, RedirectAttributes N) throws SQLException {
        M = US.ModificarUsuario(U);
        if (M.getNumero() == 0) {
            N.addFlashAttribute("mensaje", "Se editó un usuario con éxito.");
            return "redirect:/listausuarios";
        } else {
            N.addFlashAttribute("error", "Hubo un error: " + M.getMensaje());
            return "redirect:/listausuarios";
        }
    }

    @GetMapping("/EliminarUsuario/{Usuario_Id}")
    public String EliminarUsuario(@PathVariable("Usuario_Id") Long Usuario_Id, RedirectAttributes N)
            throws SQLException {
        M = US.EliminarUsuario(Usuario_Id);
        if (M.getNumero() == 0) {
            N.addFlashAttribute("mensaje", "Se eliminó un usuario con éxito.");
            return "redirect:/listausuarios";
        } else {
            N.addFlashAttribute("error", "Hubo un error: " + M.getMensaje());
            return "redirect:/listausuarios";
        }
    }

}
