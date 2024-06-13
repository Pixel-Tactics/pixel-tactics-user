package com.pixel_tactics.pixel_tactics_user.exceptions;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException() {
        super("Invalid User");
    }
}
