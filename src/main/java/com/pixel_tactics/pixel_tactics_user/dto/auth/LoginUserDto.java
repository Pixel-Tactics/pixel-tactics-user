package com.pixel_tactics.pixel_tactics_user.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserDto {
    @NotBlank(message="username is required")
    private String username;
    @NotBlank(message="password is required")
    private String password;
}
