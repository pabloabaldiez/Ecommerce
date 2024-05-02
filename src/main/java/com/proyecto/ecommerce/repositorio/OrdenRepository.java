package com.proyecto.ecommerce.repositorio;

import com.proyecto.ecommerce.modelo.Orden;
import com.proyecto.ecommerce.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer>{

}
