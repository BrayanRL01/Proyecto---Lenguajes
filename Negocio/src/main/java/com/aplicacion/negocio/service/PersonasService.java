package com.aplicacion.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplicacion.negocio.entity.Personas;
import com.aplicacion.negocio.repository.PersonasRepository;

@Service
public class PersonasService {
    
    @Autowired
    PersonasRepository PR;

    public List<Personas> ListaPersonas() {
        return PR.findAll();
    }

    
}
