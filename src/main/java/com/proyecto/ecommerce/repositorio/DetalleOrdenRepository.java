package com.proyecto.ecommerce.repositorio;

import com.proyecto.ecommerce.modelo.DetalleOrden;
import com.proyecto.ecommerce.modelo.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer>{
}
