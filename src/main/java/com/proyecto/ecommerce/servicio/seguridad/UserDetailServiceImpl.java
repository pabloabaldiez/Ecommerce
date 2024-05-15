package com.proyecto.ecommerce.servicio.seguridad;

import com.proyecto.ecommerce.modelo.Usuario;
import com.proyecto.ecommerce.servicio.usuario.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    HttpSession session;
    private Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("el username es:");
        Optional<Usuario> optionalUsu=usuarioService.findByEmail(username);

        if(optionalUsu.isPresent()){
            log.info("esto es el id de lusuario {}:", optionalUsu.get().getId());

            session.setAttribute("idusuario", optionalUsu.get().getId());
            Usuario usuario=optionalUsu.get();

            return User.builder().username(usuario.getNombre()).password(usuario.getPassword()).roles(usuario.getRol()).build();

        }else{
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}
