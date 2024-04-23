package com.example.bankmanagementsystem.Service;

import com.example.bankmanagementsystem.Model.User;
import com.example.bankmanagementsystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public List<User> getAllUsers() {
        return authRepository.findAll();
    }
}
