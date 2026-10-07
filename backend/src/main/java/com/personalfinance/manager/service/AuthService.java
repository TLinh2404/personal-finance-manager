package com.personalfinance.manager.service;

import com.personalfinance.manager.dto.RegisterRequest;
import com.personalfinance.manager.entity.User;
import com.personalfinance.manager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already registered");
        }

        if(!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Password and confirm password do not match");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(request.getFullName(), request.getEmail(), encodedPassword);
        userRepository.save(user);

    }




}