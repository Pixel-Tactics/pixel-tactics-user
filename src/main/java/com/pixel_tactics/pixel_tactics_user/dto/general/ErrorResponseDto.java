package com.pixel_tactics.pixel_tactics_user.dto.general;

import lombok.Data;

@Data
public class ErrorResponseDto {
    private String message;
    
    public ErrorResponseDto(String message) {
        this.message = message;
    }
}
