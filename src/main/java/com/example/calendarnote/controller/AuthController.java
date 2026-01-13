package com.example.calendarnote.controller;

import com.example.calendarnote.entity.User; // <--- PENTING: Pakai entity, bukan model
import com.example.calendarnote.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // AuthenticationManager dibutuhkan untuk proses cek Login (Username & Password)
    @Autowired
    private AuthenticationManager authenticationManager;

    // --- Endpoint 1: Register (Daftar Akun) ---
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            // Memanggil fungsi register di AuthService
            User newUser = authService.register(user);
            return ResponseEntity.ok(newUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Gagal Register: " + e.getMessage());
        }
    }

    // --- Endpoint 2: Login (Masuk) ---
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        try {
            // Proses autentikasi bawaan Spring Security
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // Jika berhasil (username & password cocok):
            return ResponseEntity.ok("Login Berhasil! (Selamat datang " + authentication.getName() + ")");

            // Catatan: Jika nanti pakai JWT, tokennya di-generate di sini.

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login Gagal: Username atau Password salah.");
        }
    }
}