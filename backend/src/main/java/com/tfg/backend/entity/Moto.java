package com.tfg.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @Column(name = "modelo", nullable = false, length = 150)
    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @Column(name = "cilindrada", nullable = false)
    @NotNull(message = "La cilindrada es obligatoria")
    @Min(value = 1, message = "La cilindrada debe ser mayor a 0")
    private Integer cilindrada;

    @Column(name = "anio_fabricacion", nullable = false)
    @NotNull(message = "El año es obligatorio")
    @Min(value = 1900, message = "Año no válido")
    private Integer anio;

    @Column(name = "km_actuales", nullable = false)
    private Integer kmActuales;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)  // Esta columna se creará en la tabla 'motos'

    private Usuario usuario;

}
