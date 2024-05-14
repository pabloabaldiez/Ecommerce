package com.proyecto.ecommerce.repositorio;

import com.proyecto.ecommerce.modelo.Orden;
import com.proyecto.ecommerce.modelo.Producto;
import com.proyecto.ecommerce.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Integer>{

    List<Orden> findByUsuario(Usuario usuario);

}
