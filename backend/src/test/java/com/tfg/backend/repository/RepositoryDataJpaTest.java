package com.tfg.backend.repository;

import com.tfg.backend.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RepositoryDataJpaTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Test
    void guardarYConsultarRelacionesBasicas() {
        // 1) Crear y guardar Usuario
        Usuario u = new Usuario();
        u.setNombre("Carlos");
        u.setEmail("carlos@test.com");
        u.setPassword("hash-falso-por-ahora");
        u.setFechaAlta(LocalDate.now());
        Usuario usuarioGuardado = usuarioRepository.save(u);

        // 2) Crear y guardar Moto asociada al Usuario
        Moto m = new Moto();
        m.setMarca("Honda");
        m.setModelo("CBR-R");
        m.setCilindrada(499);
        m.setAnio(2021);
        m.setKmActuales(18000);
        m.setUsuario(usuarioGuardado);
        Moto motoGuardada = motoRepository.save(m);

        // 3) Crear y guardar Mantenimiento asociado a la Moto
        Mantenimiento mant = new Mantenimiento();
        mant.setTipo(TipoMantenimiento.ACEITE);
        mant.setFecha(LocalDate.now());
        mant.setKilometros(18000);
        mant.setObservaciones("Cambio de aceite y filtro");
        mant.setMoto(motoGuardada);
        mantenimientoRepository.save(mant);

        // 4) Consultar motos por usuario_id (método personalizado)
        List<Moto> motosDelUsuario = motoRepository.findByUsuarioId(usuarioGuardado.getId());
        assertThat(motosDelUsuario).hasSize(1);
        assertThat(motosDelUsuario.get(0).getModelo()).isEqualTo("CBR-R");

        // 5) Consultar mantenimientos por moto_id (método personalizado)
        List<Mantenimiento> mantenimientosDeLaMoto = mantenimientoRepository.findByMotoId(motoGuardada.getId());
        assertThat(mantenimientosDeLaMoto).hasSize(1);
        assertThat(mantenimientosDeLaMoto.get(0).getTipo()).isEqualTo(TipoMantenimiento.ACEITE);
    }
}

