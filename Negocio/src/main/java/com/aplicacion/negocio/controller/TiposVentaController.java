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

import com.aplicacion.negocio.entity.TiposVenta;
import com.aplicacion.negocio.service.TipoVentaService;

@Controller
public class TiposVentaController {

    @Autowired
    TipoVentaService TVS;

    @GetMapping("/listatventas")
    public String Index(Model M) throws SQLException {
        List<TiposVenta> ListaVentas = TVS.ObtenerVentas();
        M.addAttribute("Ventas", ListaVentas);
        return "listatventas";
    }

    @GetMapping("/nuevotventa")
    public String CrearEstado(Model M) throws SQLException {
        M.addAttribute("titulo", "Crear Tipo de Venta");
        M.addAttribute("Venta", new TiposVenta());
        M.addAttribute("boton", "Crear");
        return "nuevotventa";
    }

    @PostMapping("/GuardarTVenta")
    public String GuardarEstado(@ModelAttribute TiposVenta TV, RedirectAttributes flash) throws SQLException {
        TVS.InsertarTipoVenta(TV);
        flash.addFlashAttribute("mensaje", "Tipo de venta creado con éxito.");
        return "redirect:/listatventas";
    }

    @GetMapping("/ModificarTVenta/{Venta_Id}")
    public String ModificarEstado(@PathVariable("Venta_Id") Long Venta_Id, Model M)
            throws SQLException {
        TiposVenta TV = TVS.ObtenerVentaPorID(Venta_Id);
        M.addAttribute("titulo", "Editar Tipo de Venta");
        M.addAttribute("Venta", TV);
        M.addAttribute("boton", "Actualizar");
        return "modificartventa";
    }

    @PostMapping("/EditarTVenta")
    public String EditarEstado(@ModelAttribute TiposVenta TV, RedirectAttributes flash) throws SQLException {
        TVS.ModificarTipoVenta(TV);
        flash.addFlashAttribute("mensaje", "Tipo de venta actualizado con éxito.");
        return "redirect:/listatventas";
    }

    @GetMapping("/EliminarTVenta/{Venta_Id}")
    public String EliminarMarca(@PathVariable("Venta_Id") Long Venta_Id, RedirectAttributes flash)
            throws SQLException {
        TVS.EliminarTipoVenta(Venta_Id);
        flash.addFlashAttribute("mensaje", "Tipo de venta eliminado con éxito.");
        return "redirect:/listatventas";
    }
}
