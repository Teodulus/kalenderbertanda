package com.example.calendarnote.entity;

import jakarta.persistence.*; // atau javax.persistence.* jika pakai Spring Boot versi lama
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users") // Nama tabel di database
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String role; // Contoh: "USER", "ADMIN"

    // Constructor Kosong (Wajib ada)
    public User() {
    }

    // Constructor dengan parameter (Opsional, untuk memudahkan)
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // --- Getter dan Setter (Wajib) ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }

    // --- Implementasi UserDetails (Wajib untuk Spring Security) ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Mengubah role menjadi Authority yang dimengerti Spring Security
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}