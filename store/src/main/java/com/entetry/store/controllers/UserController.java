package com.entetry.store.controllers;

import com.entetry.store.exception.UserNotFoundException;
import com.entetry.store.mapper.UserDetailsMapper;
import com.entetry.store.service.CustomUserDetailsService;
import com.entetry.store.service.UserServiceImpl;
import com.entetry.storecommon.dto.UserDetailsDto;
import com.entetry.storecommon.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class UserController {
    private final UserServiceImpl userService;
    private final CustomUserDetailsService userDetailsService;
    private final UserDetailsMapper userDetailsMapper;

    @Autowired
    public UserController(UserServiceImpl userService, CustomUserDetailsService userDetailsService, UserDetailsMapper userDetailsMapper) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.userDetailsMapper = userDetailsMapper;
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        System.out.println("We are here");
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public void create(@RequestBody UserDto userDto) {
        try {
            userService.create(userDto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable String id) {
        try {
            userService.delete(id);
        } catch (UserNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @PutMapping("/users")
    public void update(@RequestBody UserDto userDto) {
        try {
            userService.update(userDto);
        } catch (UserNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping("/users/userdetails")
    @ResponseBody
    public UserDetailsDto getUserDetails(@RequestParam String username) {
        try {
            return userDetailsService.loadUserByUsername(username);
        } catch (UserNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exc.getMessage(), exc);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }
}
