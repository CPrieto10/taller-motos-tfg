package com.tfg.backend.dto;

public record MotoDTO(
        Long id,
        String marca,
        String modelo,
        Integer cilindrada,
        Integer anio,
        Integer kmActuales,
        Long usuarioId
) {}