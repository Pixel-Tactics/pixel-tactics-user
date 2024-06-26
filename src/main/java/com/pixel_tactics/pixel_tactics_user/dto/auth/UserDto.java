package com.pixel_tactics.pixel_tactics_user.dto.auth;

import com.pixel_tactics.pixel_tactics_user.entities.User;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private int level;
    private int experience;
    private int curExperience;
    private int maxExperience;
    private int rating;
    
    public UserDto(User user) {
        this.username = user.getUsername();
        this.level = user.getLevel();
        this.experience = user.getExperience();
        this.curExperience = user.getCurExperience();
        this.maxExperience = user.getMaxExperience();
        this.rating = user.getRating();
    }
}
