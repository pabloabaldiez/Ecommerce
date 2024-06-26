package com.proyecto.ecommerce.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="usuarios") //me permite cambiar el nombre de la tabla para la bbdd
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Esta estrategia lo convierte en autoincrementable
    private Integer id;
    private String nombre;
    private String username;
    private String email;
    private String direccion;
    private String telefono;
    private String rol;
    private String password;


    public Usuario(Integer id, String nombre, String username, String email, String direccion, String telefono, String rol, String password) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.rol = rol;
        this.password = password;
    }

    @OneToMany(mappedBy = "usuario") //Se mapea con el atributo usuario de la clase Producto
    private List<Producto> productos;

    @OneToMany(mappedBy = "usuario") //Se mapea con el atributo usuario de la clase Orden
    private List<Orden> ordenes;


}
