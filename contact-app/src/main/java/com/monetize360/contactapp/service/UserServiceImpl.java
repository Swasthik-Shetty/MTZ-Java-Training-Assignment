package com.monetize360.contactapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetize360.contactapp.dao.UserRepository;
import com.monetize360.contactapp.dto.ContactDto;
import com.monetize360.contactapp.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public UserDto getUserById(UUID uuid) {

        return userRepository.findById(uuid)
                .map(user -> objectMapper.convertValue(user, UserDto.class))
                .orElse(null);
    }
}
