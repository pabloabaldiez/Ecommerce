package com.proyecto.ecommerce.controlador;


import com.proyecto.ecommerce.modelo.Producto;
import com.proyecto.ecommerce.servicio.orden.OrdenService;
import com.proyecto.ecommerce.servicio.producto.ProductoService;
import com.proyecto.ecommerce.servicio.usuario.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private OrdenService ordenService;

    private Logger logger= LoggerFactory.getLogger(AdministradorController.class);


    @GetMapping("")
    public String home(Model model){

        List<Producto> productos=productoService.findAll();
        model.addAttribute("productos", productos);

        return"administrador/home";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model){

        model.addAttribute("usuarios", usuarioService.findAll());

        return"administrador/usuarios";
    }

    @GetMapping("/pedidos")
    public String pedidos(Model model){

        model.addAttribute("pedidos", ordenService.findAll());

        return"administrador/pedidos";
    }

    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Integer id, Model model){

        model.addAttribute("detalles", ordenService.findById(id).get().getDetalle());

        return"administrador/detallepedido";
    }


}
