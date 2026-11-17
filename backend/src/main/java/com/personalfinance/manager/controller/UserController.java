package com.personalfinance.manager.controller;


import com.personalfinance.manager.dto.UserProfileRespone;
import com.personalfinance.manager.entity.User;
import com.personalfinance.manager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileRespone> getUserProfile(@AuthenticationPrincipal User user) {
        UserProfileRespone response = userService.getUserProfile(user);

        return ResponseEntity.ok(response);
    }
}
