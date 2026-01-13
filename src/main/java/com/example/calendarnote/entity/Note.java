package com.example.calendarnote.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date; // Tanggal note dibuat (YYYY-MM-DD)

    @Column(columnDefinition = "TEXT")
    private String content; // Isi catatan

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}