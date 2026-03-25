package com.tfg.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table (name = "mantenimientos")
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "tipo", nullable = false)
    @Enumerated(EnumType.STRING) // Guardas como texto en base de datos y evitamos problemas si el orden del enum cambia.
    @NotNull(message = "El tipo de mantenimiento es obligatorio") // Evita nulos en el Enum
    private TipoMantenimiento tipo;

    @Column (name = "fecha", nullable = false)
    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura") // Valida que no pongan un mantenimiento de mañana
    private LocalDate fecha;

    @Column (name = "kilometros", nullable = false)
    @NotNull(message = "Los kilómetros son obligatorios")
    @PositiveOrZero(message = "Los kilómetros no pueden ser negativos") // Regla lógica de negocio
    private Integer kilometros;

    @Column (name = "observaciones", nullable = true, length = 1000)
    @Size(max = 1000, message = "Las observaciones no pueden superar los 1000 caracteres")
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) //Con LAZY solo cargamos la moto cuando usamos mantenimiento.getMoto().
                                                        // Con optional = false el mantenimiento NO puede existir sin moto.
    @JoinColumn(name = "moto_id", nullable = false)  // Clave foránea que referencia a la tabla 'motos'

    private Moto moto;

}
