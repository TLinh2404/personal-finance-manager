package com.personalfinance.manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "Full name is required!")
    @Size(max = 100, message = "Full name must not exceed 100 characters!")
    private String fullName;

    @NotBlank(message = "Email is required!")
    @Email(message = "Email format is invalid!")
    private String email;

    @NotBlank(message = "Password is required!")
    @Size(min = 8, max = 100, message = "Password must have at least 8 characters!")
    private String password;

    @NotBlank(message = "Confirm password is required!")
    private String confirmPassword;

    public RegisterRequest() {
    }

    public RegisterRequest(
            String fullName,
            String email,
            String password,
            String confirmPassword
    ) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}