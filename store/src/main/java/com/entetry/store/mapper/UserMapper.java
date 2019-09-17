package com.entetry.store.mapper;

import com.entetry.store.entity.User;
import com.entetry.storecommon.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public  UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPasswordHash(user.getPasswordHash());
        userDto.setEnabled(user.isEnabled());
        userDto.setId(user.getId());
        return userDto;
    }

    public  User toUser(UserDto userDto) {
        User user = new User();
        if (userDto.getId() != null) {
            user.setId(userDto.getId());
        }
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPasswordHash(userDto.getPasswordHash());
        user.setEnabled(userDto.isEnabled());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
