package com.tfg.backend.dto;

import java.time.LocalDate;

public record UsuarioDTO(
        Long id,
        String nombre,
        String email,
        LocalDate fechaAlta
) {}