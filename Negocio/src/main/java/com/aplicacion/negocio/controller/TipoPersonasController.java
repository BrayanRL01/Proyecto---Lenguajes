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

}
