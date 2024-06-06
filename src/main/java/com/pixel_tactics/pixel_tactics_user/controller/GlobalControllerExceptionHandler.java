package com.pixel_tactics.pixel_tactics_user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pixel_tactics.pixel_tactics_user.dto.general.ErrorResponseDto;
import com.pixel_tactics.pixel_tactics_user.exceptions.InvalidInputException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception exception) {
        return ResponseEntity.internalServerError().body(new ErrorResponseDto(exception.getMessage()));
    }
    
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidInput(Exception exception) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(exception.getMessage()));
    }
}
