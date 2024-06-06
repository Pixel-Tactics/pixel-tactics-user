package com.pixel_tactics.pixel_tactics_user.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pixel_tactics.pixel_tactics_user.dto.auth.LoginUserDto;
import com.pixel_tactics.pixel_tactics_user.dto.auth.RegisterUserDto;
import com.pixel_tactics.pixel_tactics_user.entities.User;
import com.pixel_tactics.pixel_tactics_user.repository.UserRepository;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    
    public AuthService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public User register(RegisterUserDto data) {
        Optional<User> user = userRepository.findByUsername(data.getUsername());
        if (user.isPresent()) {
            throw new NoSuchElementException("User exists");
        }
        
        User newUser = new User();
        newUser.setUsername(data.getUsername());
        newUser.setPassword(passwordEncoder.encode(data.getPassword()));
        return userRepository.save(newUser);
    }
    
    public User login(LoginUserDto data) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                data.getUsername(),
                data.getPassword()
            )
        );
        return userRepository.findByUsername(data.getUsername()).orElseThrow();
    }
}
