package com.aplicacion.negocio.controller;

import com.aplicacion.negocio.entity.Tipo_Personas;
import com.aplicacion.negocio.service.TipoPersonasService;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TipoPersonasController {

    @Autowired
    TipoPersonasService tpService;

    @GetMapping("/tpPersonaLista")
    public String Index(Model M) throws SQLException {
        List<Tipo_Personas> ListRol = tpService.obtenerTipoPersonas();
        M.addAttribute("lista", ListRol);
        return "Tmplt_tpLista";
    }

    // form para crear tipoPersona
    @GetMapping("/tpersonaN")
    public String CrearUsuario(Model model) throws SQLException {
        model.addAttribute("titulo", "Crear Tipo Persona");
        model.addAttribute("usuarios", new Tipo_Personas());
        List<Tipo_Personas> ListRol = tpService.obtenerTipoPersonas();
        model.addAttribute("lista", ListRol);
        model.addAttribute("boton", "Crear");
        return "crearTpersona";
    }

    @PostMapping("/saveTPersona")
    public String GuardarUsuario(@ModelAttribute Tipo_Personas usuarios) throws SQLException {
        tpService.saveTPersonas(usuarios);
        return "redirect:/tpPersonaLista";
    }

    @PostMapping("/actualizaTPersona")
    public String actualizarTpersona(@ModelAttribute Tipo_Personas usuarios) throws SQLException {
        tpService.actualizarTPersona(usuarios);
        return "redirect:/tpPersonaLista";
    }

    @GetMapping("/editTpersona/{id}")
    public String editarTPersona(@PathVariable("id") long id_Tpersona, Model model) throws SQLException {
        Tipo_Personas usuarios = tpService.getTPersonaPorID(id_Tpersona);
        model.addAttribute("titulo", "Editar Tipo Persona");
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("boton", "Actualizar");
        return "actualizaTpersona";
    }

    @GetMapping("/deleteTpersona/{id}")
    public String eliminarUsuario(@PathVariable("id") long id_usuario) throws SQLException {
        tpService.eliminarTPersona(id_usuario);
        return "redirect:/tpPersonaLista";
    }
}
