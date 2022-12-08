/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.controller;

import com.aplicacion.negocio.entity.Detalles_Factura;
import com.aplicacion.negocio.entity.FacturaObj;
import com.aplicacion.negocio.entity.FacturaVista;
import com.aplicacion.negocio.entity.FacturasConDetalles;
import com.aplicacion.negocio.entity.MediosPago;
import com.aplicacion.negocio.entity.Mensaje;
import com.aplicacion.negocio.entity.Personas;
import com.aplicacion.negocio.entity.Productos;
import com.aplicacion.negocio.entity.TiposVenta;
import com.aplicacion.negocio.entity.Usuarios;
import com.aplicacion.negocio.service.Detalles_FacturaService;
import com.aplicacion.negocio.service.FacturasService;
import com.aplicacion.negocio.service.MediosPagoService;
import com.aplicacion.negocio.service.PersonaService;
import com.aplicacion.negocio.service.ProductosService;
import com.aplicacion.negocio.service.TipoVentaService;
import com.aplicacion.negocio.service.UsuariosService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    PersonaService personaService;

    @Autowired
    ProductosService PS;

    @Autowired
    TipoVentaService tVentaService;

    @Autowired
    MediosPagoService mPagoService;
    
    @Autowired
    UsuariosService usuarioService;

    //Para almacenar los detalles de la orden
    List<Detalles_Factura> listaDetalles = new ArrayList<>();

    List<Productos> listaProductos = new ArrayList<>();

    Mensaje msj = new Mensaje();

    List<Personas> listaPersonas = new ArrayList<>();

    List<TiposVenta> ListaTVentas = new ArrayList<>();

    List<MediosPago> ListaMedios = new ArrayList<>();
    
    List<Usuarios> listaUsuarios = new ArrayList<>();

    //Almacena los datos de la orden
    //FacturaVista factura = new FacturaVista();
    //List<Long> id_producto_guardado = new ArrayList<>();
    public void regenerarProductos() throws SQLException {
        listaProductos = PS.ObtenerProductos();
    }

    public boolean rebajarInv(Long id) {
        boolean resultado = false;
        for (Productos producto : listaProductos) {
            if (Objects.equals(producto.getId_Producto(), id)) {
                if (producto.getCantidad() > 0) {
                    producto.setCantidad(producto.getCantidad() - 1);
                    resultado = true;
                } else {
                    System.out.println("Nada que rebajar");
                    resultado = false;
                }
            }
        }
        return resultado;
    }

    public boolean rebajarInv2(Long idProducto, Long cantidad) {
        boolean resultado = false;
        for (Productos producto : listaProductos) {
            if (Objects.equals(producto.getId_Producto(), idProducto)) {
                if (producto.getCantidad() >= cantidad) {
                    producto.setCantidad(producto.getCantidad() - cantidad);
                    resultado = true;
                } else {
                    resultado = false;
                }
            }
        }
        return resultado;
    }

    public void devuelveInv(Long id, Long cant) {
        for (Productos producto : listaProductos) {
            if (Objects.equals(producto.getId_Producto(), id)) {
                producto.setCantidad(producto.getCantidad() + cant);
                break;
            }
        }
    }

    public void borraDetalle(Long id, List<Detalles_Factura> list) {
        for (Detalles_Factura detalle : list) {
            if (Objects.equals(detalle.getProductID(), id)) {
                if (detalle.getCantidad() > 0) {
                    devuelveInv(id, detalle.getCantidad());
                }
                listaDetalles.remove(detalle);
                break;
            }
        }
    }

    @GetMapping("/listaFacturas")
    public String listaFacturas(Model M) throws SQLException {
        List<FacturaVista> variable = factService.obtenerFacturasSinDetalle();
        if (variable.isEmpty()) {
            return "redirect:/home";
        } else {
            M.addAttribute("titulo", "Facturas");
            M.addAttribute("lista", variable);
            return "Tmplt_listarFacturas";
        }
    }

    @GetMapping("/verFactura/{id}")
    public String listarDetalles(Model M, @PathVariable("id") long id_factura) throws SQLException, ClassNotFoundException {
        FacturasConDetalles FacturasConDetalles = factService.obtenerFactconDetalles(id_factura);
        //System.out.println("DETALLEEEEEEEEEE: "+FacturasConDetalles.toString());
        M.addAttribute("factura", FacturasConDetalles);
        M.addAttribute("detalles", FacturasConDetalles.getListaDetalles());
        return "Tmplt_viewFactYDetalles";
    }

    @GetMapping("/facturaN") // idVendedor=2&idCliente=2&tipoVenta=1&medioPago=3&totalEntrega=456465
    public String CrearFactura(@RequestParam("idVendedor") Long idVendedor,@RequestParam("idCliente") Long idCliente,
                                @RequestParam("tipoVenta") Long tipoVenta,@RequestParam("totalEntrega") BigDecimal totalEntrega,
                                @RequestParam("medioPago") Long medioPago, Model model) throws SQLException, ClassNotFoundException {

        model.addAttribute("titulo", "Crear Factura");
        factService.crearFactura(idVendedor, idCliente, tipoVenta,  totalEntrega, medioPago, listaDetalles);
        return "redirect:/listaFacturas";
    }

    @GetMapping("/agregaRapido")
    public String agregarRapido(@RequestParam("ProductID") Long idProducto, @RequestParam("Cantidad") Long cantidad, RedirectAttributes redirAttrs, Model model) throws SQLException {

        //Objeto de los detalles de la factura
        Detalles_Factura detalleFacturas = new Detalles_Factura();

        //Obtener el producto en cuestión
        Productos producto = PS.ObtenerProductosPorID(idProducto);

        //Configuración de las variables del detalle
        detalleFacturas.setProducto(producto.getNombre());
        detalleFacturas.setProductID(producto.getId_Producto());
        detalleFacturas.setCantidad(cantidad + detalleFacturas.getCantidad());
        detalleFacturas.setPrecio(BigDecimal.valueOf(producto.getPrecio()));
        detalleFacturas.setTotalSinIva(BigDecimal.valueOf(producto.getPrecio()).multiply(BigDecimal.valueOf(detalleFacturas.getCantidad())));
        detalleFacturas.setSubtotal(detalleFacturas.getTotalSinIva().add(detalleFacturas.getTotalSinIva().multiply(BigDecimal.valueOf(0.13))));
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
        if (!ingresado) {
            //Si no se ha ingresado se añade la fila o registro a la lista global
            if (rebajarInv2(idProducto, cantidad)) {
                System.out.println("Se agrego producto");
                listaDetalles.add(detalleFacturas);
            } else {
                redirAttrs.addFlashAttribute("error", "Sin cantidad del producto");
            }

        } else if (ingresado) {
            //Si ya se ha ingresado se cambian los parámetros/campos de ese registro
            //Se busca el registro a cambiar mediante el for
            for (Detalles_Factura detalle : listaDetalles) {
                if (detalle.getProductID().equals(idProducto)) {
                    if(rebajarInv2(idProducto, cantidad)){
                    detalle.setCantidad(detalle.getCantidad() + cantidad);
                    detalle.setTotalSinIva(detalle.getTotalSinIva().add(BigDecimal.valueOf(producto.getPrecio()).multiply(BigDecimal.valueOf(detalleFacturas.getCantidad()))));
                    detalle.setSubtotal(detalle.getSubtotal().add(detalleFacturas.getTotalSinIva().add(detalleFacturas.getTotalSinIva().multiply(BigDecimal.valueOf(0.13)))));
                    }
                }
            }
        }
        model.addAttribute("titulo", "Detalles de factura");
        model.addAttribute("productos", listaProductos);
        model.addAttribute("cart", listaDetalles);

        return "redirect:/getDetalles";
    }

    @GetMapping("/masUno/{id}")
    public String masUno(@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs) {
        for (Detalles_Factura detalle : listaDetalles) {
            if (detalle.getProductID().equals(id)) {
                if (rebajarInv(id)) {
                    detalle.setCantidad(detalle.getCantidad() + 1);
                    detalle.setTotalSinIva(detalle.getTotalSinIva().add(detalle.getPrecio()));
                    detalle.setSubtotal(detalle.getSubtotal().add(detalle.getPrecio().add(detalle.getPrecio().multiply(BigDecimal.valueOf(0.13)))));
                }
            }
        }
        return "redirect:/getDetalles";
    }

    @GetMapping("/menosUno/{id}")
    public String menosUno(@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs) {
        for (Detalles_Factura detalle : listaDetalles) {
            if (detalle.getProductID().equals(id)) {
                detalle.setCantidad(detalle.getCantidad() - 1);
                detalle.setTotalSinIva(detalle.getTotalSinIva().subtract(detalle.getPrecio()));
                detalle.setSubtotal(detalle.getSubtotal().subtract(detalle.getPrecio().add(detalle.getPrecio().multiply(BigDecimal.valueOf(0.13)))));
                devuelveInv(id, 1L);
                if (detalle.getCantidad() == 0) {
                    borraDetalle(id, listaDetalles);
                    break;
                }
            }
        }
        return "redirect:/getDetalles";
    }

    @GetMapping("/nuevosDetalles/{id}/{num}")
    public String creaFactura(@PathVariable("id") Long id, @PathVariable("num") Long num, Model model, RedirectAttributes redirAttrs) throws SQLException, ClassNotFoundException {

        //Objeto de los detalles de la factura
        Detalles_Factura detalleFacturas = new Detalles_Factura();

        //Variable para cambiar la cantidad
        Long cantidad = detalleFacturas.getCantidad();

        //Obtener el producto en cuestión
        Productos producto = PS.ObtenerProductosPorID(id);
        System.out.println("PRECIOOOOOOOOOOOO: " + producto.getPrecio());
        //Configuración de las variables del detalle
        detalleFacturas.setProducto(producto.getNombre());
        detalleFacturas.setProductID(producto.getId_Producto());
        detalleFacturas.setCantidad(cantidad + 1);
        detalleFacturas.setPrecio(BigDecimal.valueOf(producto.getPrecio()));
        detalleFacturas.setIVA(BigDecimal.valueOf(0.13));
        detalleFacturas.setTotalSinIva(BigDecimal.valueOf(producto.getPrecio()).multiply(BigDecimal.valueOf(detalleFacturas.getCantidad())));
        detalleFacturas.setSubtotal(detalleFacturas.getTotalSinIva().add(detalleFacturas.getTotalSinIva().multiply(BigDecimal.valueOf(0.13))));
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
        System.out.println("Ingresado: " + ingresado);
        if (!ingresado) {
            //Si no se ha ingresado se añade la fila o registro a la lista global

            if (rebajarInv(id)) {
                System.out.println("Se agrego producto");
                listaDetalles.add(detalleFacturas);
            } else {
                redirAttrs.addFlashAttribute("error", "Sin cantidad del producto");
            }
        } else if (ingresado) {
            //Si ya se ha ingresado se cambian los parámetros/campos de ese registro
            //Se busca el registro a cambiar mediante el for
            for (Detalles_Factura detalle : listaDetalles) {
                if (detalle.getProductID().equals(id)) {

                    if(rebajarInv(id)){
                    detalle.setCantidad(detalle.getCantidad() + 1);   
                    detalle.setTotalSinIva(detalle.getTotalSinIva().add(BigDecimal.valueOf(producto.getPrecio()).multiply(BigDecimal.valueOf(detalleFacturas.getCantidad()))));
                    detalle.setSubtotal(detalle.getSubtotal().add(detalleFacturas.getTotalSinIva().add(detalleFacturas.getTotalSinIva().multiply(BigDecimal.valueOf(0.13)))));
                    } else {
                        redirAttrs.addFlashAttribute("error", "Sin cantidad del producto");
                    }
                }
            }
        }

        //Se calcula el total de la factura
        //sumaTotal = listaDetalles.stream().mapToDouble(dt -> dt.getSubtotal()).sum();
        //Se cambia el total del objeto de factura creado de forma global
        //factura.setTotal((long) sumaTotal);
        model.addAttribute("titulo", "Detalles de factura");
        model.addAttribute("productos", listaProductos);
        model.addAttribute("cart", listaDetalles);
        model.addAttribute("clientes", listaPersonas);
        model.addAttribute("deChill", new String());
        //venta y medio de pago

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
        listaPersonas = personaService.obtenerPersonas();
        model.addAttribute("clientes", listaPersonas);
        model.addAttribute("facturaOBJ", new FacturaObj());
        model.addAttribute("idRapido", new String());
        //model.addAttribute("cantRapido",new Long(0L));

        //model.addAttribute("idCliente",new Long(0L));
        ListaTVentas = tVentaService.ObtenerVentas();
        model.addAttribute("tipoVenta", ListaTVentas);
        //model.addAttribute("tVenta",new Long(0L));

        ListaMedios = mPagoService.ObtenerMPagos();
        model.addAttribute("mediosPago", ListaMedios);
        //model.addAttribute("mPago",new Long(0L));
        
        listaUsuarios = usuarioService.ObtenerUsuarios();
        model.addAttribute("usuarios",listaUsuarios);
        
        model.addAttribute("tEntrega",new Long(0L));
        return "Tmplt_Factura";
    }

    @GetMapping("/nuevaFact")
    public String mostrarDetalles(Model model) throws SQLException {
        regenerarProductos();
        listaDetalles = new ArrayList<>();

        model.addAttribute("productos", listaProductos);
        //Son las variables globales
        model.addAttribute("cart", listaDetalles);
        listaPersonas = personaService.obtenerPersonas();
        model.addAttribute("clientes", listaPersonas);

        model.addAttribute("idRapido", new String());
        model.addAttribute("cantRapido", new Long(0L));

        model.addAttribute("idCliente", new Long(0L));

        ListaTVentas = tVentaService.ObtenerVentas();
        model.addAttribute("tipoVenta", ListaTVentas);
        model.addAttribute("tVenta", new Long(0L));

        ListaMedios = mPagoService.ObtenerMPagos();
        model.addAttribute("mediosPago", ListaMedios);
        model.addAttribute("mPago", new Long(0L));

        model.addAttribute("tEntrega", new Long(0L));
        //tEntrega, mPago, tVenta, idCliente, 
        model.addAttribute("facturaOBJ", new FacturaObj());
        
        listaUsuarios = usuarioService.ObtenerUsuarios();
        model.addAttribute("usuarios",listaUsuarios);
        //model.addAttribute("orden", factura);
        model.addAttribute("deChill", new String());
        return "Tmplt_Factura";
    }

    @GetMapping("/borrarDetalle/{id}")
    public String borrarDetalle(@PathVariable("id") Long id, Model model) throws SQLException {
        //List<Productos> listaProductos = PS.ObtenerProductos();
        borraDetalle(id, listaDetalles);
        model.addAttribute("productos", listaProductos);
        //Son las variables globales
        model.addAttribute("cart", listaDetalles);
        //model.addAttribute("orden", factura);
        return "Tmplt_Factura";
    }
    
    @GetMapping("/borrarFactura/{id}")
    public String borrarFactura(@PathVariable("id") Long id, Model model, RedirectAttributes redirAttrs) throws SQLException {
        //List<Productos> listaProductos = PS.ObtenerProductos();                
        msj=factService.borrarFactura(id);
        if(msj.getNumero() == 1){//falla
            redirAttrs.addFlashAttribute("error", "Error: "+msj.getMensaje());
            return "redirect:/listaFacturas";
        }
        else {
            redirAttrs.addFlashAttribute("success", msj.getMensaje());
            return "redirect:/listaFacturas"; //1
        } 
        //model.addAttribute("orden", factura);
        
    }

    

}
