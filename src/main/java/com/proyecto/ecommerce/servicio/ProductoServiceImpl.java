package com.proyecto.ecommerce.servicio;

import com.proyecto.ecommerce.modelo.Producto;
import com.proyecto.ecommerce.repositorio.ProductoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    ProductoRespository productoRespository;

    @Override
    public Producto save(Producto producto) {
        return productoRespository.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return productoRespository.findById(id);
    }

    @Override
    public void update(Producto producto) {
        productoRespository.save(producto);
    }

    @Override
    public void delete(Integer id) {
        productoRespository.deleteById(id);
    }

    @Override
    public List<Producto> listaProductos() {
        return productoRespository.findAll();
    }
}
