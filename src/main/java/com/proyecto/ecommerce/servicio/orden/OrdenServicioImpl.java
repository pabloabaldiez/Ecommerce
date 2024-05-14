package com.proyecto.ecommerce.servicio.orden;

import com.proyecto.ecommerce.modelo.Orden;
import com.proyecto.ecommerce.modelo.Usuario;
import com.proyecto.ecommerce.repositorio.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenServicioImpl implements OrdenService{

    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }

    public String generarNumeroOrden(){

        int numero=0;
        String numeroString="";

       List<Orden> ordenes= findAll();
       List<Integer> numeros=new ArrayList<Integer>();

       ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));

        if(ordenes.isEmpty()){

            numero=1;

        }else{

            numero=numeros.stream().max(Integer::compare).get();
            numero++;
        }

        if(numero<10){
            numeroString="000000000"+String.valueOf(numero);
        }else if(numero<100){
            numeroString="00000000"+String.valueOf(numero);
        } else if(numero<1000){
            numeroString="0000000"+String.valueOf(numero);
    }

        return numeroString;
    }
    @Override
    public List<Orden> findByUsuario(Usuario usuario) {
        return ordenRepository.findByUsuario(usuario);
    }

    @Override
    public Optional<Orden> findById(Integer id) {
        return ordenRepository.findById(id);
    }

}
