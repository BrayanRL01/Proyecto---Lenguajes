package com.aplicacion.negocio.controller;

import com.aplicacion.negocio.entity.Detalles_Facturas;
import com.aplicacion.negocio.entity.Facturas;
import com.aplicacion.negocio.repository.DetallesFacturasRepository;
import com.aplicacion.negocio.service.FacturasService;
import com.aplicacion.negocio.service.MarcasService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FacturasController {

    @Autowired
    FacturasService facturasService;

    /*@Autowired
    DetallesFacturasService dFacturasService;*/
    @Autowired
    MarcasService marcasService;

    //Para almacenar los detalles de la orden
    List<Detalles_Facturas> listaDetalles = new ArrayList<>();

    //Almacena los datos de la orden
    Facturas factura = new Facturas();

    @GetMapping("/detalles")
    public String crearFactura(@RequestParam(required=false, name = "nombre_producto") String nombre, Model model) {
        
        //Objeto de los detalles de la factura
        Detalles_Facturas detalleFacturas = new Detalles_Facturas();
        
        //Variable para el total
        double sumaTotal = 0;
        
        //Variable para cambiar la cantidad
        int cantidad = detalleFacturas.getCANTIDAD();

        //Lista para evitar datos duplicados
        List<Detalles_Facturas> facturaNueva = new ArrayList<>();
        
        //Configuración de las variables del detalle
        detalleFacturas.setPRODUCTO(nombre);
        detalleFacturas.setCANTIDAD(cantidad + 1);
        detalleFacturas.setPRECIO(Math.round(Math.random() * 1500));
        detalleFacturas.setTOTAL_SIN_IVA(detalleFacturas.getPRECIO() * detalleFacturas.getCANTIDAD());
        detalleFacturas.setSUBTOTAL(detalleFacturas.getTOTAL_SIN_IVA() * 1.13);

        //Validar que el producto no se añada 2 veces
        String producto = detalleFacturas.getPRODUCTO();
        boolean ingresado = listaDetalles.stream().anyMatch(p -> p.getPRODUCTO().equals(producto));
        
        if(!ingresado){
            //Si no se ha ingresado se añade la fila o registro a la lista global
            listaDetalles.add(detalleFacturas);
            
        } else if (ingresado){
            //Si ya se ha ingresado se cambian los parámetros/campos de ese registro
            //Se busca el registro a cambiar mediante el for
            for (Detalles_Facturas detalle : listaDetalles) {
                
                if (detalle.getPRODUCTO().equals(producto)){
                    detalle.setCANTIDAD(detalle.getCANTIDAD() + 1);
                    detalle.setSUBTOTAL(detalle.getPRECIO() * detalle.getCANTIDAD());
                    facturaNueva.add(detalle);
                    
                } else if (!detalle.getPRODUCTO().equals(producto)){
                    //Si ya se ha cambiado se añade a la lista temporal creada antes
                    facturaNueva.add(detalle);
                }
            }
            //Se hace el cambio de la lista global igualandola con la lista temporal
            listaDetalles = facturaNueva;
        }
        
        //listaDetalles.add(detalleFacturas);
        
        //Se calcula el total de la factura
        sumaTotal = listaDetalles.stream().mapToDouble(dt -> dt.getSUBTOTAL()).sum();
        
        //Se cambia el total del objeto de factura creado de forma global
        factura.setTOTAL(Math.round((int) sumaTotal));
        
        model.addAttribute("cart", listaDetalles);
        model.addAttribute("orden", factura);
        
        System.out.println(producto + ingresado);
        
        
        return "carrito";
    }
    
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
    }
    
    @GetMapping("/getDetalles")
    public String getCarrito(Model model) {
        //Son variables globales
        model.addAttribute("cart", listaDetalles);
        model.addAttribute("orden", factura);

        return "carrito";
    }
    
    //Guardar la orden
    /*@GetMapping("/saveOrden")
    public String saveOrden() {
        //Fecha de registro
        Date fechaCreacion = new Date();
        
        //Se "setea" la fecha de creación de la entidad creada de manera global
        factura.setFECHA_HORA_VENTA(fechaCreacion);
        
        //Según el método que se ponga, se genera un número para la factura/orden
        //factura.setNumero(ordenService.generarNumeroOrden());
        
        //Guardar orden
        //ordenService.save(orden);

        //Guardar detalles
        for (Detalles_Facturas dt : listaDetalles) {
            dt.setOrden(factura);
            detalleOrdenService.save(dt);
        }

        //Limpiar orden y detalles
        factura = new Facturas();
        listaDetalles.clear();

        return "redirect:/menu";
    }*/
}

