package com.example.calendarnote.controller;

import com.example.calendarnote.model.Note;
import com.example.calendarnote.model.User;
import com.example.calendarnote.repository.NoteRepository;
import com.example.calendarnote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public List<Note> getMyNotes() {
        return noteRepository.findByUser_Id(getCurrentUser().getId());
    }

    @PostMapping
    public Note saveNote(@RequestBody Note request) {
        User user = getCurrentUser();
        Optional<Note> existingNote = noteRepository.findByUser_IdAndDate(user.getId(), request.getDate());

        Note note;
        if (existingNote.isPresent()) {
            note = existingNote.get();
            note.setContent(request.getContent());
        } else {
            note = new Note();
            note.setDate(request.getDate());
            note.setContent(request.getContent());
            note.setUser(user);
        }
        return noteRepository.save(note);
    }
}