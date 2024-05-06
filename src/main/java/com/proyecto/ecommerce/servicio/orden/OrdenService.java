package com.proyecto.ecommerce.servicio.orden;

import com.proyecto.ecommerce.modelo.Orden;

import java.util.List;

public interface OrdenService {

    Orden save(Orden orden);
    List<Orden> findAll();
    public String generarNumeroOrden();

}
