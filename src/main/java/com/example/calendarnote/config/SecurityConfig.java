package com.example.calendarnote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
                .csrf(csrf -> csrf.disable()) // Mematikan CSRF untuk memudahkan tes API
                .authorizeHttpRequests(auth -> auth
                        // Izinkan akses publik ke endpoint auth (Login/Register) dan file statis
                        .requestMatchers("/api/auth/**", "/", "/index.html", "/register", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated() // Sisanya harus login
                )
                .httpBasic(Customizer.withDefaults()); // Menggunakan basic auth (opsional)

        return http.build();
    }

    // --- BAGIAN INI YANG TADI HILANG ---
    // Bean ini wajib ada agar AuthController bisa memanggil authenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}