package com.proyecto.ecommerce.modelo;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name="productos") //me permite cambiar el nombre de la tabla para la bbdd
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Esta estrategia lo convierte en autoincrementable
    private Integer id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private double precio;
    private int cantidad;

    @ManyToOne
    private  Usuario usuario;

}
