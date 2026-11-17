package com.personalfinance.manager.controller;


import com.personalfinance.manager.dto.UpdateUserProfileRequest;
import com.personalfinance.manager.dto.UserProfileResponse;
import com.personalfinance.manager.entity.User;
import com.personalfinance.manager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileResponse> getUserProfile(@AuthenticationPrincipal User user) {
        UserProfileResponse response = userService.getUserProfile(user);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/me")
    public ResponseEntity<UserProfileResponse> updateUserProfile(@AuthenticationPrincipal User user, @Valid @RequestBody UpdateUserProfileRequest request) {
        UserProfileResponse response = userService.updateUserProfile(user, request);

        return ResponseEntity.ok(response);
    }
}
