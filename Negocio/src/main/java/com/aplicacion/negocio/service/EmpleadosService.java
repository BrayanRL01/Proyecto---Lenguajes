package com.aplicacion.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplicacion.negocio.entity.Empleados;
import com.aplicacion.negocio.repository.EmpleadosRepository;

@Service
public class EmpleadosService {
    @Autowired
    EmpleadosRepository ER;

    public List<Empleados> ListaEmpleados() {
        return ER.findAll();
    }
}
