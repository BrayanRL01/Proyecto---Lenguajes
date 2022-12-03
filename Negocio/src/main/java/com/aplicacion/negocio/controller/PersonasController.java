package com.aplicacion.negocio.controller;

import com.aplicacion.negocio.entity.Mensaje;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    Mensaje msj = new Mensaje();
    
    @GetMapping("/")
    public String home(){
        return "home";
    }
    
    @GetMapping("/home")
    public String home2(){
        return "home";
    }

    @GetMapping("/personaLista")
    public String index(Model M) throws SQLException {
        // List<Tipo_Personas> tpLista = tpService.lista();
        List<Personas> variable = personaService.obtenerPersonas();
        // System.out.println ("variableeeeeeeeeeeee: "+variable);
        M.addAttribute("titulo", "Personas");
        M.addAttribute("lista", variable);
        return "Tmplt_pLista";
    }

    // form para crear persona
    @GetMapping("/personaN")
    public String CrearUsuario(Model model) throws SQLException {
        List<Tipo_Personas> listaTipoPersonas = tpService.obtenerTipoPersonas();
        model.addAttribute("accion", "añadiendo");
        model.addAttribute("prefijo", "a");
        model.addAttribute("titulo", "Personas");
        model.addAttribute("usuarios", new Personas());
        model.addAttribute("tipoPersonas", listaTipoPersonas);
        model.addAttribute("boton", "Añadir");
        return "crearPersona";
    }

    // accion de guardar la nueva persona desde el html de crearPersona por eso el
    // postMapping
    @PostMapping("/savePersona")
    public String GuardarUsuario(@ModelAttribute Personas usuarios, RedirectAttributes redirAttrs) throws SQLException {
        msj= personaService.savePersonas(usuarios);
        if(msj.getNumero() == 1){//falla
            redirAttrs.addFlashAttribute("error", "Error: "+msj.getMensaje());
            return "redirect:/personaLista";
        }
        else {
            redirAttrs.addFlashAttribute("success", "Nueva persona guardada correctamente");
            return "redirect:/personaLista"; //1
        }
            
    }
    
    @PostMapping("/actualizaPersona")
    public String actualizarPersona(@ModelAttribute Personas usuarios, RedirectAttributes redirAttrs) throws SQLException {
        msj = personaService.actualizarPersona(usuarios);
        if(msj.getNumero()==1){ //falla
            redirAttrs.addFlashAttribute("error", "Error: "+msj.getMensaje());
            return "redirect:/personaLista";
        }
        else{
            redirAttrs.addFlashAttribute("success", "Se edito Persona con ID: "+usuarios.getId_persona());
            return "redirect:/personaLista";
        }
    }   
    
    @GetMapping("/editUsuario/{id}")
    public String editarPersona(@PathVariable("id") long id_persona, Model model) throws SQLException {
        Personas usuarios = personaService.getPersonaPorID(id_persona);
        List<Tipo_Personas> tipoPersonas = tpService.obtenerTipoPersonas();
        model.addAttribute("accion", "editando");
        model.addAttribute("prefijo", "de");
        model.addAttribute("titulo", "Personas");
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("tipoPersonas", tipoPersonas);
        model.addAttribute("boton", "Actualizar");
        return "actualizaPersona";
    }

    @GetMapping("/deleteusuario/{id}")
    public String eliminarUsuario(@PathVariable("id") long id_usuario, RedirectAttributes redirAttrs) throws SQLException {
        msj = personaService.eliminarPersona(id_usuario);
        if(msj.getNumero()==1){ //falla
            redirAttrs.addFlashAttribute("error", "Error: "+msj.getMensaje());
            return "redirect:/personaLista";
        }
        else{
            redirAttrs.addFlashAttribute("success", "Se elimino Persona con ID: "+id_usuario);
            return "redirect:/personaLista";
        }
    }
}
