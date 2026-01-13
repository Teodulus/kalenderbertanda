package com.example.calendarnote.repository;

import com.example.calendarnote.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser_Id(Long userId);
    Optional<Note> findByUser_IdAndDate(Long userId, LocalDate date);
}