package com.tfg.backend.service;

import com.tfg.backend.entity.Mantenimiento;
import com.tfg.backend.entity.Moto;
import com.tfg.backend.exception.ResourceNotFoundException;
import com.tfg.backend.repository.MantenimientoRepository;
import com.tfg.backend.repository.MotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MantenimientoService {

    private final MantenimientoRepository mantenimientoRepository;
    private final MotoRepository motoRepository;

    public MantenimientoService(MantenimientoRepository mantenimientoRepository, MotoRepository motoRepository) {
        this.mantenimientoRepository = mantenimientoRepository;
        this.motoRepository = motoRepository;
    }

    public Mantenimiento crear(Long motoId, Mantenimiento mantenimiento) {
        Moto moto = motoRepository.findById(motoId)
                .orElseThrow(() -> new ResourceNotFoundException("Moto no encontrada"));

        mantenimiento.setMoto(moto);
        return mantenimientoRepository.save(mantenimiento);
    }

    public Mantenimiento obtenerPorId(Long id) {
        return mantenimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mantenimiento no encontrado"));
    }

    public List<Mantenimiento> listarPorMoto(Long motoId) {
        return mantenimientoRepository.findByMotoId(motoId);
    }

    public void eliminar(Long id) {
        if (!mantenimientoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Mantenimiento no encontrado");
        }
        mantenimientoRepository.deleteById(id);
    }
}
