package com.example.calendarnote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Fungsi: Kalau ada yang buka link utama "/", langsung lempar ke index.html
    @GetMapping("/")
    public String home() {
        return "redirect:/index.html";
    }
}