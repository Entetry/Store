package com.entetry.store.mapper;

import com.entetry.store.entity.User;
import com.entetry.storecommon.dto.UserDto;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPasswordHash(user.getPasswordHash());
        userDto.setEnabled(user.isEnabled());
        userDto.setId(user.getId());
        return userDto;
    }

    public static User toUser(UserDto userDto) {
        User user = new User();
        if (userDto.getId() != null) {
            user.setId(userDto.getId());
        }
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(userDto.getPasswordHash());
        user.setEnabled(userDto.isEnabled());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
