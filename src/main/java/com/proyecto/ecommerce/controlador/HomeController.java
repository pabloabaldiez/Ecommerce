package com.proyecto.ecommerce.controlador;

import com.proyecto.ecommerce.modelo.DetalleOrden;
import com.proyecto.ecommerce.modelo.Orden;
import com.proyecto.ecommerce.modelo.Producto;
import com.proyecto.ecommerce.repositorio.ProductoRespository;
import com.proyecto.ecommerce.servicio.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger LOOGER= LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoService productoService;

    //Para almacenar los detalles de las ordenes
    List<DetalleOrden> detalles= new ArrayList<DetalleOrden>();

    //Datos orden
    Orden orden= new Orden();

    @GetMapping("")
    public String home(Model model){

        model.addAttribute("productos", productoService.findAll());

        return "usuario/home";
    }

    @GetMapping("/productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model){

        Producto producto= new Producto();
        Optional<Producto> productoOptional=productoService.getProducto(id);
        producto=productoOptional.get();
        model.addAttribute("producto", producto);


        return "usuario/productohome";
    }

    @PostMapping("/carrito")
    public String addCarrito(@RequestParam Integer id, @RequestParam Integer cantidad, Model model){

        DetalleOrden detalleOrden= new DetalleOrden();
        Producto producto=new Producto();
        double sumaTotal=0;

        Optional<Producto> optionalProducto=productoService.getProducto(id);

        //LOOGER.info("Producto añadido: {}", optionalProducto.get());
        //LOOGER.info("Cantidad: {}", cantidad);

        producto=optionalProducto.get();

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio()*cantidad);
        detalleOrden.setProducto(producto);

        sumaTotal=detalles.stream()
                .mapToDouble(DetalleOrden::getTotal)
                .sum();

        orden.setTotal(sumaTotal);

        detalles.add(detalleOrden);

        model.addAttribute("carrito", detalles);
        model.addAttribute("orden", orden);

        return"usuario/carrito";
    }

}
