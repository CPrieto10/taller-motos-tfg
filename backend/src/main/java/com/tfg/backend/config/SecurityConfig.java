// Crea este archivo en src/main/java/com/tfg/backend/config/SecurityConfig.java
package com.tfg.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Seguimos desactivándolo para facilitar los POST desde JS
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/login.html", "/registro.html", "/css/**", "/js/**").permitAll() // Archivos públicos
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll() // Permitir registro
                        .requestMatchers("/api/auth/login").permitAll() // Permitir login
                        .anyRequest().authenticated() // Todo lo demás (motos, mantenimientos) protegido
                )
                // En lugar de JWT, usamos login básico o por formulario para mantener la sesión
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}