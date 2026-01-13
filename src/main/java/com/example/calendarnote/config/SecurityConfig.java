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
                // Matikan CSRF agar kita bisa kirim data Register dengan mudah
                .csrf(csrf -> csrf.disable())

                // Aturan Siapa yang Boleh Masuk
                .authorizeHttpRequests(auth -> auth
                        // Izinkan akses ke Register, Halaman Utama, CSS, JS tanpa Login
                        .requestMatchers("/", "/index.html", "/register", "/css/**", "/js/**").permitAll()
                        // Sisanya (termasuk API Notes) WAJIB Login
                        .anyRequest().authenticated()
                )

                // Gunakan Login standar (Popup browser atau form login kita)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // Alat untuk mengacak (enkripsi) password agar aman
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}