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

import com.aplicacion.negocio.entity.Marcas;
import com.aplicacion.negocio.service.MarcasService;

@Controller
public class MarcasController {

    @Autowired
    MarcasService MS;

    @GetMapping("/listamarcas")
    public String Index(Model M) throws SQLException {
        List<Marcas> ListaMarcas = MS.ObtenerMarcas();
        M.addAttribute("TAB_MARCAS", ListaMarcas);
        return "listamarcas";
    }

    @GetMapping("/nuevamarca")
    public String CrearMarca(Model M) throws SQLException {
        M.addAttribute("titulo", "Crear Marca");
        M.addAttribute("Marcas", new Marcas());
        M.addAttribute("boton", "Crear");
        return "nuevamarca";
    }

    @PostMapping("/GuardarMarca")
    public String GuardarMarca(@ModelAttribute Marcas M) throws SQLException {
        MS.InsertarMarcas(M);
        return "redirect:/listamarcas";
    }

    

    @GetMapping("/ModificarMarca/{Id_Marca}")
    public String EditarMarca(@PathVariable("Id_Marca") Long Id_Marca, Model M) throws SQLException {
        Marcas Marca = MS.ObtenerMarcaPorID(Id_Marca);
        M.addAttribute("titulo", "Editar Persona");
        M.addAttribute("Marcas", Marca);
        M.addAttribute("boton", "Actualizar");
        return "modificarmarca";
    }

    @GetMapping("/EliminarMarca/{Id_Marca}")
    public String EliminarMarca(@PathVariable("Id_Marca") Long Id_Marca) throws SQLException {
        MS.EliminarMarca(Id_Marca);
        return "redirect:/listamarcas";

    }

}
