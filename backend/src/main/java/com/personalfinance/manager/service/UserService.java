package com.personalfinance.manager.service;

import com.personalfinance.manager.dto.ChangePasswordRequest;
import com.personalfinance.manager.dto.UpdateUserProfileRequest;
import com.personalfinance.manager.dto.UserProfileResponse;
import com.personalfinance.manager.entity.User;
import com.personalfinance.manager.exception.InvalidCredentialsException;
import com.personalfinance.manager.exception.PasswordMismatchException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.personalfinance.manager.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserProfileResponse getUserProfile(User user) {
        return new UserProfileResponse(user.getId(),  user.getFullName(), user.getEmail());
    }

    public UserProfileResponse updateUserProfile(User user, UpdateUserProfileRequest request) {
        user.setFullName(request.getFullName());


        User updatedUser = userRepository.save(user);

        return new UserProfileResponse(updatedUser.getId(), updatedUser.getFullName(), updatedUser.getEmail());

    }

    public void changePassword(User user, ChangePasswordRequest request) {
        // 1. xác thực mật khẩu hiện tại
        if(!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Current password is incorrect!");
        }

        // 2. check 2 password có khớp với nhau khong
        if(!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new PasswordMismatchException("Password and confirm new password do not match");
        }

        // 3. encode mat khau moi
        String encodedPassword  = passwordEncoder.encode(request.getNewPassword());

        // 4. cap nhat mat khau moi
        user.setPassword(encodedPassword);

        // 5. lưu mat khau moi
        userRepository.save(user);


    }
}
