package com.coursework.dean_auto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public ResponseEntity login(){
        try {
            return ResponseEntity.ok().body("Successfully logged in!");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Invalid credentials!");
        }
    }
}
