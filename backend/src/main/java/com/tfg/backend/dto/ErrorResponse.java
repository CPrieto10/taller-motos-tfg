package com.tfg.backend.dto;

public record ErrorResponse(
        int status,
        String message,
        long timestamp
) {}
