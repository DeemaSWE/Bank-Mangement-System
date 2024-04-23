package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.Api.ApiResponse;
import com.example.bankmanagementsystem.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body(new ApiResponse("Welcome Back!"));
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body(new ApiResponse("Thank You!"));
    }

    // Admin
    @GetMapping("/get-all")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

}
