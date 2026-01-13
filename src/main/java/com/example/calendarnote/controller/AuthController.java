package com.example.calendarnote.controller;

import com.example.calendarnote.model.User;
import com.example.calendarnote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Endpoint: Menerima pendaftaran user baru
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        // 1. Cek apakah Username sudah ada di database?
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("Maaf, Username sudah dipakai orang lain!");
        }

        // 2. Acak password supaya aman (Enkripsi)
        String passwordRahasia = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordRahasia);

        // 3. Set Role default jadi 'USER'
        user.setRole("USER");

        // 4. Simpan ke Database
        userRepository.save(user);

        return ResponseEntity.ok("Registrasi Berhasil! Silakan Login.");
    }
}