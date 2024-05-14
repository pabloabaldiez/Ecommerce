package com.proyecto.ecommerce.servicio.orden;

import com.proyecto.ecommerce.modelo.Orden;
import com.proyecto.ecommerce.modelo.Usuario;

import java.util.List;
import java.util.Optional;

public interface OrdenService {

    Orden save(Orden orden);
    List<Orden> findAll();
    String generarNumeroOrden();
    List<Orden> findByUsuario(Usuario usuario);
    Optional<Orden> findById(Integer id);




}
