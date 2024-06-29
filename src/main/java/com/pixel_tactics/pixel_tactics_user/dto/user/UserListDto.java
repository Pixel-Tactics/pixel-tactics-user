package com.pixel_tactics.pixel_tactics_user.dto.user;

import java.util.List;

import lombok.Data;

@Data
public class UserListDto {
    private List<UserPublicDto> users;
    
    public UserListDto(List<UserPublicDto> users) {
        this.users = users;
    }
}
