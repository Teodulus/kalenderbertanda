package com.example.calendarnote.config;

import com.example.calendarnote.entity.User;
import com.example.calendarnote.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Cek apakah database Supabase masih kosong?
        if (userRepository.count() == 0) {
            // Buat User Admin
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");

            // Buat User Biasa
            User budi = new User();
            budi.setUsername("budi");
            budi.setPassword(passwordEncoder.encode("budi123"));
            budi.setRole("USER");

            userRepository.save(admin);
            userRepository.save(budi);

            System.out.println("✅ DATA SEEDER: User 'admin' dan 'budi' berhasil dibuat di Supabase!");
        }
    }
}