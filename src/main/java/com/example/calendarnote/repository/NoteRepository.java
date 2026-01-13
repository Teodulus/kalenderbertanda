package com.example.calendarnote.repository;

import com.example.calendarnote.model.Note; // Pastikan import model
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUserId(Long userId);
    Optional<Note> findByUserIdAndDate(Long userId, LocalDate date);
}