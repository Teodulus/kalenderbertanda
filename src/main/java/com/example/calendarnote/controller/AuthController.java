package com.example.calendarnote.controller;

import com.example.calendarnote.entity.User;
import com.example.calendarnote.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Endpoint untuk Register User Baru
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        authService.register(user);
        return "User berhasil didaftarkan! Silakan login.";
    }

    // Endpoint Cek Login (Dipanggil oleh HTML Frontend)
    @GetMapping("/check")
    public String checkLogin() {
        return "Login Berhasil";
    }
}