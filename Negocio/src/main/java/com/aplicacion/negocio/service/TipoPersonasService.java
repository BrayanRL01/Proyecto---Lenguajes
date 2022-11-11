/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.service;

import com.aplicacion.negocio.entity.Tipo_Personas;
import com.aplicacion.negocio.repository.TipoPersonasRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author XPC
 */
@Service
public class TipoPersonasService {

    @Autowired
    TipoPersonasRepository tpRepository;

    public List<Tipo_Personas> lista() {
        return tpRepository.findAll();
    }
}
