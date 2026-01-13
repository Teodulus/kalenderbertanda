package com.example.calendarnote.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // Menggunakan columnDefinition TEXT agar bisa menyimpan tulisan panjang
    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt;

    // --- BAGIAN INI YANG KEMARIN HILANG/KURANG ---
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    // ---------------------------------------------

    // Constructor Kosong (Wajib)
    public Note() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructor dengan isi
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // --- Getter dan Setter (Wajib Ada) ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // --- INI OBAT ERRORNYA (Getter Setter untuk User) ---
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}