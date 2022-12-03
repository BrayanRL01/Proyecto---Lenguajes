/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.controller;

import com.aplicacion.negocio.entity.Detalles_Factura;
import com.aplicacion.negocio.entity.FacturaVista;
import com.aplicacion.negocio.entity.FacturasConDetalles;
import com.aplicacion.negocio.entity.Productos;
import com.aplicacion.negocio.service.Detalles_FacturaService;
import com.aplicacion.negocio.service.FacturasService;
import com.aplicacion.negocio.service.ProductosService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author XPC
 */
@Controller
public class FacturasController {

    @Autowired
    FacturasService factService;

    @Autowired
    Detalles_FacturaService detallesService;

    @Autowired
    ProductosService PS;
    
    //Para almacenar los detalles de la orden
    List<Detalles_Factura> listaDetalles = new ArrayList<>();
    
    List<Productos> listaProductos = new ArrayList<>();

    //Almacena los datos de la orden
    //FacturaVista factura = new FacturaVista();
    //List<Long> id_producto_guardado = new ArrayList<>();
    public void regenerarProductos() throws SQLException{
        listaProductos = PS.ObtenerProductos();
    }
    
    @GetMapping("/listaFacturas")
    public String listaFacturas(Model M) throws SQLException {
        List<FacturaVista> variable = factService.obtenerFacturasSinDetalle();
        if (variable.isEmpty()) {
            M.addAttribute("lista", variable);
            return "redirect:/personaLista";
        } else {
            return "Tmplt_listarFacturas";
        }
    }

    @GetMapping("/verFactura/{id}")
    public String listarDetalles(Model M, @PathVariable("id") long id_factura) throws SQLException, ClassNotFoundException {
        FacturasConDetalles FacturasConDetalles = factService.obtenerFactconDetalles(id_factura);
        M.addAttribute("factura", FacturasConDetalles);
        M.addAttribute("detalles", FacturasConDetalles.getListaDetalles());
        return "Tmplt_viewFactYDetalles";
    }

    @GetMapping("/facturaN")
    public String CrearFactura(Model model) throws SQLException, ClassNotFoundException {

        model.addAttribute("titulo", "Crear Factura");
        factService.crearFactura();
        return "Tmplt_listarFacturas";
    }


    @GetMapping("/nuevosDetalles/{id}/{num}")
    public String creaFactura(@PathVariable("id") Long id, @PathVariable("num") Long num, Model model) throws SQLException, ClassNotFoundException {

        //Objeto de los detalles de la factura
        Detalles_Factura detalleFacturas = new Detalles_Factura();

        //Variable para el total
        double sumaTotal = 0;

        //Variable para cambiar la cantidad
        Long cantidad = detalleFacturas.getCantidad();

        //Obtener el producto en cuestión
        Productos producto = PS.ObtenerProductosPorID(id);

        //Configuración de las variables del detalle
        detalleFacturas.setProducto(producto.getNombre());
        detalleFacturas.setProductID(producto.getId_Producto());
        detalleFacturas.setCantidad(cantidad + 1);
        detalleFacturas.setPrecio(producto.getPrecio());
        detalleFacturas.setTotalSinIva(producto.getPrecio() * detalleFacturas.getCantidad());
        detalleFacturas.setSubtotal(detalleFacturas.getTotalSinIva() * (long) 1.13);
        detalleFacturas.setTamano(producto.getTamano());

        //Validar que el producto no se añada 2 veces
        //Long id_producto_actual = producto.getId_Producto();
        boolean ingresado = false; //listaDetalles.stream().anyMatch(p -> p.getProducto().equals(producto.getNombre()));

        for (int i = 0; i < listaDetalles.size(); i++) {
            if (listaDetalles.get(i).getProductID().equals(producto.getId_Producto())) {
                ingresado = true;
                break;
            } else {
                ingresado = false;
            }
        }
        System.out.println("Valor de ingresado: " + ingresado);
        if (!ingresado) {
            //Si no se ha ingresado se añade la fila o registro a la lista global
            listaDetalles.add(detalleFacturas);
            
            System.out.println("Se agrego producto");
        } else if (ingresado) {
            //Si ya se ha ingresado se cambian los parámetros/campos de ese registro
            //Se busca el registro a cambiar mediante el for
            for (Detalles_Factura detalle : listaDetalles) {

                if (detalle.getProductID().equals(producto.getId_Producto())) {
                    detalle.setCantidad(detalle.getCantidad() + 1);
                    detalle.setTotalSinIva(detalle.getPrecio() * detalle.getCantidad());
                    detalle.setSubtotal(detalle.getTotalSinIva() * (long) 1.13);
                }
            }
        }

        //Se calcula el total de la factura
        sumaTotal = listaDetalles.stream().mapToDouble(dt -> dt.getSubtotal()).sum();

        //Se cambia el total del objeto de factura creado de forma global
        //factura.setTotal((long) sumaTotal);
        model.addAttribute("titulo", "Detalles de factura");
        model.addAttribute("productos", listaProductos);
        model.addAttribute("cart", listaDetalles);

        System.out.println(producto.getNombre() + " " + ingresado);

        return "redirect:/getDetalles";
    }

    @GetMapping("/getDetalles")
    public String getCarrito(Model model) throws SQLException {
        //List<Productos> listaProductos = PS.ObtenerProductos();
        
        model.addAttribute("productos", listaProductos);
        //Son las variables globales
        model.addAttribute("cart", listaDetalles);
        //model.addAttribute("orden", factura);

        return "Tmplt_Factura";
    }
        @GetMapping("/nuevaFact")
    public String nuevaFact(Model model) throws SQLException {
        regenerarProductos();
        listaDetalles = new ArrayList<>();
        model.addAttribute("productos", listaProductos);
        //Son las variables globales
        model.addAttribute("cart", listaDetalles);
        //model.addAttribute("orden", factura);

        return "Tmplt_Factura";
    }
    /* Lógica que se puede usar para borrar productos de los detalles a agregar
    
    @GetMapping("/delete/detalles/{nombre_producto}")
    public String borrarProductoCarrito(@PathVariable String nombre_producto, Model model) {
        
        //Lista nueva de productos
        List<Detalles_Facturas> facturaNueva = new ArrayList<>();

        //Se recorre la lista global
        for (Detalles_Facturas detalle : listaDetalles) {
            
            //Si el producto no es el mismo que el indicado se agrega a la lista creada líneas antes
            if (!detalle.getPRODUCTO().equals(nombre_producto)) {
                facturaNueva.add(detalle);
            }
        }

        //Poner la nueva lista con los productos restantes
        listaDetalles = facturaNueva;

        //Se recalcula el total
        double sumaTotal = 0;
        sumaTotal = listaDetalles.stream().mapToDouble(dt -> dt.getSUBTOTAL()).sum();

        factura.setTOTAL((int) sumaTotal);
        model.addAttribute("cart", listaDetalles);
        model.addAttribute("orden", factura);

        return "redirect:/getDetalles";
    }*/
}
