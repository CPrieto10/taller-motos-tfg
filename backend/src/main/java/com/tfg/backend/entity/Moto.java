package com.tfg.backend.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "motos")
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "marca", nullable = false, length = 150)
    private String marca;

    @Column(name = "modelo", nullable = false, length = 150)
    private String modelo;

    @Column(name = "cilindrada", nullable = false)
    private Integer cilindrada;

    @Column(name = "anio_fabricacion", nullable = false)
    private Integer anio;

    @Column(name = "km_actuales", nullable = false)
    private Integer kmActuales;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)  // Esta columna se creará en la tabla 'motos'
    private Usuario usuario;

}
