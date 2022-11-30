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

import com.aplicacion.negocio.entity.MediosPago;
import com.aplicacion.negocio.service.MediosPagoService;

@Controller
public class MediosPagoController {

    @Autowired
    MediosPagoService MPS;

    @GetMapping("/listampagos")
    public String Index(Model M) throws SQLException {
        List<MediosPago> ListaMedios = MPS.ObtenerMPagos();
        M.addAttribute("Medios", ListaMedios);
        return "listampagos";
    }

    @GetMapping("/nuevompago")
    public String CrearMPago(Model M) throws SQLException {
        M.addAttribute("titulo", "Crear Medio de Pago");
        M.addAttribute("Pago", new MediosPago());
        M.addAttribute("boton", "Crear");
        return "nuevompago";
    }

    @PostMapping("/GuardarMPago")
    public String GuardarMPago(@ModelAttribute MediosPago MP, RedirectAttributes flash) throws SQLException {
        MPS.InsertarMedioPago(MP);
        flash.addFlashAttribute("mensaje", "Medio de pago creado con éxito.");
        return "redirect:/listampagos";
    }

    @GetMapping("/ModificarMPago/{Medio_Id}")
    public String ModificarMPago(@PathVariable("Medio_Id") Long Medio_Id, Model M)
            throws SQLException {
        MediosPago MP = MPS.ObtenerMedioPorID(Medio_Id);
        M.addAttribute("titulo", "Editar Medio de Pago");
        M.addAttribute("Pago", MP);
        M.addAttribute("boton", "Actualizar");
        return "modificarmpago";
    }

    @PostMapping("/EditarMPago")
    public String EditarMPago(@ModelAttribute MediosPago MP, RedirectAttributes flash) throws SQLException {
        MPS.ModificarMedioPago(MP);
        flash.addFlashAttribute("mensaje", "Medio de pago actualizado con éxito.");
        return "redirect:/listampagos";
    }

    @GetMapping("/EliminarMPago/{Medio_Id}")
    public String EliminarMPago(@PathVariable("Medio_Id") Long Medio_Id, RedirectAttributes flash)
            throws SQLException {
        MPS.EliminarMedioPago(Medio_Id);
        flash.addFlashAttribute("mensaje", "Medio de pago eliminado con éxito.");
        return "redirect:/listampagos";
    }

}
