package com.tfg.backend.service;

import com.tfg.backend.entity.Moto;
import com.tfg.backend.entity.Usuario;
import com.tfg.backend.exception.ResourceNotFoundException;
import com.tfg.backend.repository.MotoRepository;
import com.tfg.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    private final MotoRepository motoRepository;
    private final UsuarioRepository usuarioRepository;

    public MotoService(MotoRepository motoRepository, UsuarioRepository usuarioRepository) {
        this.motoRepository = motoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Moto crear(Long usuarioId, Moto moto) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        moto.setUsuario(usuario);
        return motoRepository.save(moto);
    }

    public Moto obtenerPorId(Long id) {
        return motoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Moto no encontrada"));
    }

    public List<Moto> listarPorUsuario(Long usuarioId) {
        return motoRepository.findByUsuarioId(usuarioId);
    }

    public void eliminar(Long id) {
        if (!motoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Moto no encontrada");
        }
        motoRepository.deleteById(id);
    }
}
