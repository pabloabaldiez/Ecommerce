package com.proyecto.ecommerce.controlador;

import com.proyecto.ecommerce.modelo.Usuario;
import com.proyecto.ecommerce.servicio.usuario.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String registro(){

        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario){

        logger.info("Usuario registro: {}", usuario);
        usuario.setRol("USER");
        usuarioService.save(usuario);
        return "redirect:/";
    }

}
