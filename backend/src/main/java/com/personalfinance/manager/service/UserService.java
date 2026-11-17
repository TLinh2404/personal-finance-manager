package com.personalfinance.manager.service;

import com.personalfinance.manager.dto.UpdateUserProfileRequest;
import com.personalfinance.manager.dto.UserProfileResponse;
import com.personalfinance.manager.entity.User;
import org.springframework.stereotype.Service;
import com.personalfinance.manager.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfileResponse getUserProfile(User user) {
        return new UserProfileResponse(user.getId(),  user.getFullName(), user.getEmail());
    }

    public UserProfileResponse updateUserProfile(User user, UpdateUserProfileRequest request) {
        user.setFullName(request.getFullName());


        User updatedUser = userRepository.save(user);

        return new UserProfileResponse(updatedUser.getId(), updatedUser.getFullName(), updatedUser.getEmail());

    }
}
