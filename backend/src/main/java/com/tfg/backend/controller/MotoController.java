package com.tfg.backend.controller;

import com.tfg.backend.dto.MotoDTO;
import com.tfg.backend.entity.Moto;
import com.tfg.backend.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motos")

public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @PostMapping("/usuario/{usuarioId}")
    public ResponseEntity<MotoDTO> crear(@PathVariable Long usuarioId,@Valid @RequestBody Moto moto) {
        Moto nueva = motoService.crear(usuarioId, moto);
        return new ResponseEntity<>(convertToDTO(nueva), HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MotoDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        List<MotoDTO> motos = motoService.listarPorUsuario(usuarioId).stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(convertToDTO(motoService.obtenerPorId(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        motoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private MotoDTO convertToDTO(Moto m) {
        return new MotoDTO(
                m.getId(),
                m.getMarca(),
                m.getModelo(),
                m.getCilindrada(),
                m.getAnio(),
                m.getKmActuales(),
                m.getUsuario().getId()
        );
    }
}