package com.proyecto.ecommerce.controlador;

import com.proyecto.ecommerce.modelo.Usuario;
import com.proyecto.ecommerce.servicio.usuario.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


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

    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }

    @PostMapping("/autenticacion")
    public String Autenticacion(Usuario usuario, HttpSession session){
        logger.info("Autenticacion : {}", usuario);

        Optional<Usuario> usuarioLogeo=usuarioService.findByEmail(usuario.getEmail());

        logger.info("Usuario obtenido : {}", usuarioLogeo.get());

        if(usuarioLogeo.isPresent()) {
            session.setAttribute("idusuario", usuarioLogeo.get().getId());
            if (usuarioLogeo.get().getRol().equals("ADMIN")){
                return "redirect:/administrador";
            }else{
                return "redirect:/";}

        }else{logger.info("Usuario no existe");
            }

        return"redirect:/";
    }

}
