package com.tfg.backend.controller;

import com.tfg.backend.entity.Moto;
import com.tfg.backend.service.MotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motos")
@CrossOrigin(origins = "*")
public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<Moto> crear(@PathVariable Long usuarioId, @RequestBody Moto moto) {
        return new ResponseEntity<>(motoService.crear(usuarioId, moto), HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(motoService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(motoService.obtenerPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        motoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}