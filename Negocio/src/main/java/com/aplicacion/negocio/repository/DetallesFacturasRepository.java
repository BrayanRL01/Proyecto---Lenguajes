package com.aplicacion.negocio.repository;

import com.aplicacion.negocio.entity.Detalles_Facturas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallesFacturasRepository extends JpaRepository<Detalles_Facturas, Integer>{
    
    
}
