package com.aplicacion.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplicacion.negocio.entity.Personas;

@Repository
public interface PersonasRepository extends JpaRepository<Personas, Integer> {

}
