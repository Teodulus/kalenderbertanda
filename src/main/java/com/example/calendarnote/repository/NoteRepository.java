package com.example.calendarnote.repository;
import com.example.calendarnote.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    // Cari note berdasarkan User dan Tanggal (1 tanggal cuma boleh 1 note per user)
    Optional<Note> findByUserIdAndDate(Long userId, LocalDate date);

    // Ambil semua note milik user tertentu
    List<Note> findByUserId(Long userId);
}