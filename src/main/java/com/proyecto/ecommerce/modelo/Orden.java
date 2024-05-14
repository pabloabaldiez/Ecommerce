package com.proyecto.ecommerce.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ordenes")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Esta estrategia lo convierte en autoincrementable
    private Integer id;
    private String numero;
    private Date fechaCreacion;
    private Date fechaRecibida;
    private Double total;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "orden")
    private List<DetalleOrden> detalle;
}
