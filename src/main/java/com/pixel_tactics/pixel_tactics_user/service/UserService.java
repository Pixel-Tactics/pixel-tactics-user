package com.pixel_tactics.pixel_tactics_user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pixel_tactics.pixel_tactics_user.entities.User;
import com.pixel_tactics.pixel_tactics_user.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(
        UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }
    
    public List<User> getUsersInUsernameList(List<String> usernameList) {
        return userRepository.findByUsernameIn(usernameList);
    }
}
