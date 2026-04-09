package com.tfg.backend.controller;

import com.tfg.backend.dto.MantenimientoDTO;
import com.tfg.backend.entity.Mantenimiento;
import com.tfg.backend.service.MantenimientoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mantenimientos")

public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    public MantenimientoController(MantenimientoService mantenimientoService) {
        this.mantenimientoService = mantenimientoService;
    }

    @PostMapping("/moto/{motoId}")
    public ResponseEntity<MantenimientoDTO> crear(@PathVariable Long motoId, @Valid @RequestBody Mantenimiento mantenimiento) {
        Mantenimiento nuevo = mantenimientoService.crear(motoId, mantenimiento);
        return new ResponseEntity<>(convertToDTO(nuevo), HttpStatus.CREATED);
    }

    @GetMapping("/moto/{motoId}")
    public ResponseEntity<List<MantenimientoDTO>> listarPorMoto(@PathVariable Long motoId) {
        List<MantenimientoDTO> lista = mantenimientoService.listarPorMoto(motoId).stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MantenimientoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(convertToDTO(mantenimientoService.obtenerPorId(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mantenimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private MantenimientoDTO convertToDTO(Mantenimiento m) {
        return new MantenimientoDTO(
                m.getId(),
                m.getTipo(),
                m.getFecha(),
                m.getKilometros(),
                m.getObservaciones(),
                m.getMoto().getId()
        );
    }
}