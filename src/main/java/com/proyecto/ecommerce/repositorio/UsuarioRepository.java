package com.proyecto.ecommerce.repositorio;

import com.proyecto.ecommerce.modelo.Producto;
import com.proyecto.ecommerce.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
}
