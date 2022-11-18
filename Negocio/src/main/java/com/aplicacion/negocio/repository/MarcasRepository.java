package com.aplicacion.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplicacion.negocio.entity.Marcas;

@Repository
public interface MarcasRepository extends JpaRepository<Marcas, Integer> {

}
