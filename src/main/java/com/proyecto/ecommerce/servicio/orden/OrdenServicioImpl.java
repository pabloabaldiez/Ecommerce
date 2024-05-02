package com.proyecto.ecommerce.servicio.orden;

import com.proyecto.ecommerce.modelo.Orden;
import com.proyecto.ecommerce.repositorio.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenServicioImpl implements OrdenService{

    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }
}
