package com.aplicacion.negocio.repository;

import com.aplicacion.negocio.entity.Personas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PersonaRepository extends JpaRepository<Personas, Integer>{
    
}
