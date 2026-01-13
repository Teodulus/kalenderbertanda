package com.example.calendarnote.repository;

import com.example.calendarnote.entity.User; // <--- PERHATIKAN: Ini harus 'entity', BUKAN 'model'
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Method ini wajib ada karena dipanggil di AuthService (loadUserByUsername)
    Optional<User> findByUsername(String username);

    // Method tambahan (opsional) untuk mengecek apakah username sudah terpakai saat register
    Boolean existsByUsername(String username);
}