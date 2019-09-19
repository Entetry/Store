package com.entetry.store.controllers;

import com.entetry.store.exception.UserNotFoundException;
import com.entetry.store.service.CustomUserDetailsService;
import com.entetry.store.service.UserServiceImpl;
import com.entetry.storecommon.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController {
    private final UserServiceImpl userService;
    private final CustomUserDetailsService userDetailsService;
    @Autowired
    public UserController(UserServiceImpl userService,CustomUserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService=userDetailsService;
    }

    @GetMapping("/user")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user")
    public void create(@RequestBody UserDto userDto) {
        try {
            userService.create(userDto);
        } catch (UserNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }

    @DeleteMapping("/user")
    public void delete(@RequestBody UserDto userDto) {
        try {
            userService.delete(userDto);
        } catch (UserNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }

    @PutMapping("/user")
    public void update(@RequestBody UserDto userDto) {
        try {
            userService.update(userDto);
        } catch (UserNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
    @GetMapping("/users/userdetails")
    @ResponseBody
    public UserDetails getUserDetails(@RequestParam String username) {
        try {
            return userDetailsService.loadUserByUsername(username);
        } catch (UserNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        }
    }
}
