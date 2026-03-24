package com.tfg.backend.controller;

import com.tfg.backend.entity.Mantenimiento;
import com.tfg.backend.service.MantenimientoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mantenimientos")
@CrossOrigin(origins = "*")
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    public MantenimientoController(MantenimientoService mantenimientoService) {
        this.mantenimientoService = mantenimientoService;
    }

    @PostMapping("/moto/{motoId}")
    public ResponseEntity<Mantenimiento> crear(@PathVariable Long motoId, @RequestBody Mantenimiento mantenimiento) {
        return new ResponseEntity<>(mantenimientoService.crear(motoId, mantenimiento), HttpStatus.CREATED);
    }

    @GetMapping("/moto/{motoId}")
    public ResponseEntity<List<Mantenimiento>> listarPorMoto(@PathVariable Long motoId) {
        return ResponseEntity.ok(mantenimientoService.listarPorMoto(motoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mantenimiento> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(mantenimientoService.obtenerPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        mantenimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}