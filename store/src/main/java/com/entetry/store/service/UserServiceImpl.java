package com.entetry.store.service;

import com.entetry.store.entity.User;
import com.entetry.store.exception.UserNotFoundException;
import com.entetry.store.mapper.UserMapper;
import com.entetry.store.persistense.UserRepository;
import com.entetry.storecommon.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public void create(UserDto userDto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDto.setPasswordHash(bCryptPasswordEncoder.encode(userDto.getPasswordHash()));
        try {
            userRepository.save(userMapper.toUser(userDto));
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

    @Transactional
    public void delete(String id) {
        User user = userRepository.findById(Long.parseLong(id)).orElseThrow(UserNotFoundException::new);
        try {
            userRepository.delete(user);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

    @Transactional
    public void update(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(UserNotFoundException::new);
        User updatedUser = userMapper.toUser(userDto);
        try {
            userRepository.save(updatedUser);
        } catch (Exception e) {
            LOGGER.error("an exception occured!", e);
            throw e;
        }
    }

    @Transactional
    public List<UserDto> getAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).map(userMapper::toUserDto).collect(Collectors.toList());
    }
}


