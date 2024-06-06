package com.pixel_tactics.pixel_tactics_user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pixel_tactics.pixel_tactics_user.dto.auth.LoginUserDto;
import com.pixel_tactics.pixel_tactics_user.dto.auth.RegisterUserDto;
import com.pixel_tactics.pixel_tactics_user.dto.auth.TokenDto;
import com.pixel_tactics.pixel_tactics_user.dto.auth.UserDto;
import com.pixel_tactics.pixel_tactics_user.entities.User;
import com.pixel_tactics.pixel_tactics_user.exceptions.InvalidInputException;
import com.pixel_tactics.pixel_tactics_user.service.AuthService;
import com.pixel_tactics.pixel_tactics_user.service.JWTService;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final JWTService jwtService;
    private final AuthService authService;
    
    public AuthController(
        JWTService jwtService,
        AuthService authService
    ) {
        this.jwtService = jwtService;
        this.authService = authService;
    }
    
    @GetMapping("/current")
    public ResponseEntity<UserDto> current() {
        User user = getCurrentUser();
        return ResponseEntity.ok(new UserDto(user));
    }
    
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto payload) {
        User registeredUser = authService.register(payload);
        return ResponseEntity.ok(registeredUser);
    }
    
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginUserDto payload) {
        try {
            User user = authService.login(payload);
            String jwtToken = jwtService.generateToken(user);
            TokenDto response = new TokenDto();
            response.setToken(jwtToken);
            return ResponseEntity.ok(response);
        } catch (Exception exception) {
            throw new InvalidInputException("Username or password is invalid");
        }
    }
    
    private User getCurrentUser() {
        return (User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
    }
}
