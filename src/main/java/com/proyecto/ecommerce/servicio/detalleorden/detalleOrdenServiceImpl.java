package com.proyecto.ecommerce.servicio.detalleorden;

import com.proyecto.ecommerce.modelo.DetalleOrden;
import com.proyecto.ecommerce.repositorio.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class detalleOrdenServiceImpl implements detalleOrdenServicio {

    @Autowired
    private DetalleOrdenRepository detalleOrdenRepository;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }
}
