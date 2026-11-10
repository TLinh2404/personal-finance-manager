package com.personalfinance.manager.controller;

import com.personalfinance.manager.dto.RegisterRequest;
import com.personalfinance.manager.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.personalfinance.manager.dto.LoginRequest;
import com.personalfinance.manager.dto.LoginResponse;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // đăng ký
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
       authService.register(request);

       return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successful!");
    }

    // đăng nhập
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        String accessToken = authService.login(request);

        LoginResponse response = new LoginResponse("Login success", accessToken, "Bearer");

        return ResponseEntity.ok(response);

    }




}
