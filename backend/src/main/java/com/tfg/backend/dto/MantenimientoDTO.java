package com.tfg.backend.dto;

import com.tfg.backend.entity.TipoMantenimiento;
import java.time.LocalDate;

public record MantenimientoDTO(
        Long id,
        TipoMantenimiento tipo,
        LocalDate fecha,
        Integer kilometros,
        String observaciones,
        Long motoId
) {}