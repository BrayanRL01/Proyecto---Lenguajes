package com.aplicacion.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aplicacion.negocio.entity.Empleados;

@Repository
public interface EmpleadosRepository extends JpaRepository<Empleados, Integer>{
    
    
}
