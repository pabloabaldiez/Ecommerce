package com.proyecto.ecommerce.modelo;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name="detalles")
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Esta estrategia lo convierte en autoincrementable
    private Integer id;
    private  String nombre;
    private double cantidad;
    private double precio;
    private double total;

    @ManyToOne
    private Orden orden;

    @ManyToOne
    private Producto producto;

}
