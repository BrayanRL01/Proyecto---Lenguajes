package com.aplicacion.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplicacion.negocio.entity.Tipo_Personas;

@Repository
public interface TipoPersonasRepository extends JpaRepository<Tipo_Personas, Integer> {

}
