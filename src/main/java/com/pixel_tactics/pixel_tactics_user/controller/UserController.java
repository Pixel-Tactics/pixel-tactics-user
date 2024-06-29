package com.pixel_tactics.pixel_tactics_user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pixel_tactics.pixel_tactics_user.dto.user.GetUsersDto;
import com.pixel_tactics.pixel_tactics_user.dto.user.UserListDto;
import com.pixel_tactics.pixel_tactics_user.dto.user.UserPublicDto;
import com.pixel_tactics.pixel_tactics_user.entities.User;
import com.pixel_tactics.pixel_tactics_user.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;
    
    public UserController(
        UserService userService
    ) {
        this.userService = userService;
    }
    
    @GetMapping("/users-from-list")
    public ResponseEntity<UserListDto> getAllUsersInUsernameList(@RequestBody GetUsersDto payload) {
        List<User> userList = userService.getUsersInUsernameList(payload.getUsernameList());
        List<UserPublicDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(new UserPublicDto(user));
        }
        return ResponseEntity.ok(new UserListDto(userDtoList));
    }
}
