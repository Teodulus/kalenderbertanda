package com.example.calendarnote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Matikan CSRF untuk fitur register sederhana
                .authorizeHttpRequests(auth -> auth
                        // URL ini BOLEH diakses siapa saja (tanpa login)
                        .requestMatchers("/", "/index.html", "/register", "/css/**", "/js/**").permitAll()
                        // Sisanya HARUS Login
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // Login standar

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Alat pengacak password
    }
}