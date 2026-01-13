package com.example.calendarnote.controller;

// --- BAGIAN PENTING: IMPORT HARUS BENAR ---
import com.example.calendarnote.entity.Note; // Gunakan entity.Note
import com.example.calendarnote.entity.User; // Gunakan entity.User (BUKAN model.User)
import com.example.calendarnote.repository.NoteRepository;
import com.example.calendarnote.repository.UserRepository;
// -------------------------------------------

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    // --- Helper Method: Mengambil User yang sedang Login ---
    private User getCurrentUser() {
        // Mengambil data autentikasi dari Spring Security
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Mencari user di database berdasarkan username (email/nama)
        return userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan di database!"));
    }

    // --- Endpoint 1: Mengambil Catatan milik User Sendiri ---
    @GetMapping
    public List<Note> getMyNotes() {
        User user = getCurrentUser();
        // Pastikan di NoteRepository ada method: findByUser_Id(Long userId) atau findByUser(User user)
        return noteRepository.findByUser_Id(user.getId());
    }

    // --- Endpoint 2: Membuat Catatan Baru ---
    @PostMapping
    public Note createNote(@RequestBody Note note) {
        // Set pemilik catatan otomatis ke user yang sedang login
        User user = getCurrentUser();
        note.setUser(user);

        return noteRepository.save(note);
    }
}