package com.pixel_tactics.pixel_tactics_user.dto.user;

import com.pixel_tactics.pixel_tactics_user.entities.User;

import lombok.Data;

@Data
public class UserPublicDto {
    private String username;
    private int level;
    private int rating;
    
    public UserPublicDto(User user) {
        this.username = user.getUsername();
        this.level = user.getLevel();
        this.rating = user.getRating();
    }
}
