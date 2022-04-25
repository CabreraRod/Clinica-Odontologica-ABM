package com.example.clinica.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class UserController {
    @GetMapping("/")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("<h1> INGRESASTE AL SISTEMA </h1>");
    }

    @GetMapping("/user")
    public ResponseEntity<String> user(){
        return ResponseEntity.ok("<h1> INGRESASTE AL USER </h1>");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin(){
        return ResponseEntity.ok("<h1> INGRESASTE AL ADMIN </h1>");
    }
}
