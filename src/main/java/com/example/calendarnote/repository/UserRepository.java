package com.example.calendarnote.repository;

import com.example.calendarnote.model.User; // Pastikan import model
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}