package com.aplicacion.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplicacion.negocio.entity.Productos;
import com.aplicacion.negocio.repository.ProductosRepository;

@Service
public class ProductosService {
    
    @Autowired
    ProductosRepository PR;

    public List<Productos> ListaProductos() {
        return PR.findAll();
    }
}
