package com.proyecto.ecommerce.servicio;

import com.proyecto.ecommerce.modelo.Producto;

import java.util.Optional;
import java.util.List;

public interface ProductoService {

    public Producto save(Producto producto);
    public Optional<Producto> get(Integer id);
    public void update(Producto producto);
    public void delete(Integer id);
    public List<Producto> listaProductos();

}
