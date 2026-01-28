package com.tfg.backend.entity;

import jakarta.persistence.*;
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
    private TipoMantenimiento tipo;

    @Column (name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column (name = "kilometros", nullable = false)
    private Integer kilometros;

    @Column (name = "observaciones", nullable = true, length = 1000)
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY, optional = false) //Con LAZY solo cargamos la moto cuando usamos mantenimiento.getMoto().
                                                        // Con optional = false el mantenimiento NO puede existir sin moto.
    @JoinColumn(name = "moto_id", nullable = false)  // Clave foránea que referencia a la tabla 'motos'
    private Moto moto;

}
