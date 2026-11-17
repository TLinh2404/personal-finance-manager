package com.personalfinance.manager.service;

import com.personalfinance.manager.dto.UserProfileRespone;
import com.personalfinance.manager.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserProfileRespone getUserProfile(User user) {
        return new UserProfileRespone(user.getId(),  user.getFullName(), user.getEmail());
    }
}
