package com.proyecto.ecommerce.servicio.usuario;

import com.proyecto.ecommerce.modelo.Usuario;
import com.proyecto.ecommerce.repositorio.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface UsuarioService {
    Optional<Usuario> findById(Integer id);
}