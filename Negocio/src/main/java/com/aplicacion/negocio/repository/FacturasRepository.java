package com.aplicacion.negocio.repository;

import com.aplicacion.negocio.entity.Facturas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturasRepository extends JpaRepository<Facturas, Integer>{
    
    
}
