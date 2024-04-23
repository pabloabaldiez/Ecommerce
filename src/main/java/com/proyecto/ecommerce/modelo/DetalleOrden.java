package com.proyecto.ecommerce.modelo;

import jakarta.persistence.Entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class DetalleOrden {
    private Integer id;
    private  String nombre;
    private double cantidad;
    private double precio;
    private double total;

}
