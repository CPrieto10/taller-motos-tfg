package com.tfg.backend.controller;

import com.tfg.backend.dto.LoginRequest;
import com.tfg.backend.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        return usuarioRepository.findByEmail(login.email())
                .filter(user -> passwordEncoder.matches(login.password(), user.getPassword()))
                .map(user -> ResponseEntity.ok().body("{\"message\": \"Login exitoso\", \"userId\": " + user.getId() + "}"))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Credenciales incorrectas\"}"));
    }
}