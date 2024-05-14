package com.proyecto.ecommerce.controlador;

import com.proyecto.ecommerce.modelo.DetalleOrden;
import com.proyecto.ecommerce.modelo.Orden;
import com.proyecto.ecommerce.modelo.Usuario;
import com.proyecto.ecommerce.servicio.orden.OrdenService;
import com.proyecto.ecommerce.servicio.usuario.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private OrdenService ordenService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/registro")
    public String registro(){

        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario){

        //logger.info("Usuario registro: {}", usuario);
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

        Optional<Usuario> usuarioLogeado=usuarioService.findByEmail(usuario.getEmail());

       // logger.info("Usuario obtenido : {}", usuarioLogeo.get());

        if(usuarioLogeado.isPresent()) {
            session.setAttribute("idusuario", usuarioLogeado.get().getId());
            if (usuarioLogeado.get().getRol().equals("ADMIN")){
                return "redirect:/administrador";
            }else{
                return "redirect:/";}

        }else{logger.info("Usuario no existe");
            }

        return"redirect:/";
    }

    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session){
        model.addAttribute("sesion", session.getAttribute("idusuario"));

        Usuario usuario=usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        List<Orden> orden=ordenService.findByUsuario(usuario);

        model.addAttribute("ordenes", orden);

        return "usuario/compras";
    }

    @GetMapping("/detalle/{id}")
    public String getDetalleCompra(@PathVariable Integer id, HttpSession session, Model model){

        logger.info("Id de la orden: {} ", id);

        Optional<Orden> orden=ordenService.findById(id);

        logger.info("la lista es: {}", orden.isPresent());

        model.addAttribute("detalles", orden.get().getDetalle());

        model.addAttribute("sesion", session.getAttribute("idusuario"));

        return"usuario/detallecompra";
    }

    @GetMapping("/cerrar")
    public String cierreSesion(HttpSession session){

        session.removeAttribute("idusuario");

        return "redirect:/";
    }

}
