package com.example.calendarnote.controller;

import com.example.calendarnote.entity.Note;
import com.example.calendarnote.entity.User;
import com.example.calendarnote.repository.NoteRepository;
import com.example.calendarnote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // 1. Ambil semua note user untuk ditampilkan di kalender
    @GetMapping
    public List<Note> getMyNotes() {
        return noteRepository.findByUserId(getCurrentUser().getId());
    }

    // 2. Simpan atau Update Note berdasarkan Tanggal
    @PostMapping
    public Note saveNote(@RequestBody Note request) {
        User user = getCurrentUser();

        // Cek apakah di tanggal itu user sudah punya note?
        Optional<Note> existingNote = noteRepository.findByUserIdAndDate(user.getId(), request.getDate());

        Note note;
        if (existingNote.isPresent()) {
            // Update
            note = existingNote.get();
            note.setContent(request.getContent());
        } else {
            // Baru
            note = new Note();
            note.setDate(request.getDate());
            note.setContent(request.getContent());
            note.setUser(user);
        }
        return noteRepository.save(note);
    }
}