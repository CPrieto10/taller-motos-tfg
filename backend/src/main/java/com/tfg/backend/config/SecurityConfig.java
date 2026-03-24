// Crea este archivo en src/main/java/com/tfg/backend/config/SecurityConfig.java
package com.tfg.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Crucial para permitir POST desde Postman
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}