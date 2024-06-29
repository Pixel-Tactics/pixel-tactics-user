package com.pixel_tactics.pixel_tactics_user.dto.user;

import java.util.List;

import lombok.Data;

@Data
public class GetUsersDto {
    private List<String> usernameList;
}
