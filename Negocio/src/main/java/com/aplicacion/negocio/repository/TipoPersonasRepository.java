/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplicacion.negocio.repository;

import com.aplicacion.negocio.entity.Tipo_Personas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author XPC
 */
@Repository
public interface TipoPersonasRepository extends JpaRepository<Tipo_Personas, Integer>{
       
}
