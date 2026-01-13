package com.example.calendarnote.repository;

import com.example.calendarnote.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    // Mencari note berdasarkan ID user
    List<Note> findByUser_Id(Long userId);
}