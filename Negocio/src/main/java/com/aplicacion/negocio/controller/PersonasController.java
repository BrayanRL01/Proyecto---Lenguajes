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

import com.aplicacion.negocio.entity.Personas;
import com.aplicacion.negocio.entity.Tipo_Personas;
import com.aplicacion.negocio.service.PersonaService;
import com.aplicacion.negocio.service.TipoPersonasService;

/**
 *
 * @author XPC
 */
@Controller
public class PersonasController {
    
    @Autowired
    PersonaService personaService;

    @Autowired
    TipoPersonasService tpService;

    @GetMapping("/personaLista")
    public String index(Model M) throws SQLException {
        // List<Tipo_Personas> tpLista = tpService.lista();
        List<Personas> variable = personaService.obtenerPersonas();
        // System.out.println ("variableeeeeeeeeeeee: "+variable);
        M.addAttribute("lista", variable);
        return "Tmplt_pLista";
    }

    // form para crear persona
    @GetMapping("/personaN")
    public String CrearUsuario(Model model) throws SQLException {
        List<Tipo_Personas> listaTipoPersonas = tpService.obtenerTipoPersonas();
        model.addAttribute("titulo", "Crear Persona");
        model.addAttribute("usuarios", new Personas());
        model.addAttribute("tipoPersonas", listaTipoPersonas);
        model.addAttribute("boton", "Crear");

        return "crearPersona";
    }

    // accion de guardar la nueva persona desde el html de crearPersona por eso el
    // postMapping
    @PostMapping("/savePersona")
    public String GuardarUsuario(@ModelAttribute Personas usuarios) throws SQLException {
        personaService.actualizarPersona(usuarios);
        return "redirect:/personaLista";
    }

    @GetMapping("/editUsuario/{id}")
    public String editarPersona(@PathVariable("id") long id_persona, Model model) throws SQLException {
        Personas usuarios = personaService.getPersonaPorID(id_persona);
        List<Tipo_Personas> tipoPersonas = tpService.obtenerTipoPersonas();
        model.addAttribute("titulo", "Editar Persona");
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("tipoPersonas", tipoPersonas);
        model.addAttribute("boton", "Actualizar");
        return "crearPersona";
    }

    @GetMapping("/deleteusuario/{id}")
    public String eliminarUsuario(@PathVariable("id") long id_usuario) throws SQLException {
        personaService.eliminarPersona(id_usuario);
        return "redirect:/personaLista";

    }
}
