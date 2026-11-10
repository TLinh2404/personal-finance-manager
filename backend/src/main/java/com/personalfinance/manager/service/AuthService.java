package com.personalfinance.manager.service;

import com.personalfinance.manager.security.JwtService;
import com.personalfinance.manager.dto.RegisterRequest;
import com.personalfinance.manager.entity.User;
import com.personalfinance.manager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.personalfinance.manager.exception.EmailAlreadyExistsException;
import com.personalfinance.manager.exception.PasswordMismatchException;
import com.personalfinance.manager.dto.LoginRequest;
import com.personalfinance.manager.exception.InvalidCredentialsException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,  JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

    }

    // đăng ký
    public void register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email is already registered");
        }

        if(!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("Password and confirm password do not match");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(request.getFullName(), request.getEmail(), encodedPassword);
        userRepository.save(user);

    }

    // đăng nhập
    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Invalid email or password"));

    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        if(!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
    }

    private String generateAccessToken(User user) {
        return jwtService.generateToken(user.getId());
    }

    public String login(LoginRequest request) {
        User user =  findUserByEmail(request.getEmail());

        validatePassword(request.getPassword(), user.getPassword());

        return generateAccessToken(user);

    }

}