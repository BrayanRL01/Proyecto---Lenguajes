package com.aplicacion.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplicacion.negocio.entity.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Integer>{
    
}
