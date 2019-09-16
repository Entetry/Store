package com.entetry.store.controller;

import com.entetry.store.service.UserServiceImpl;
import com.entetry.storecommon.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/store/users")
    public void create(@RequestBody UserDto userDto) {
        userService.create(userDto);
    }
}
