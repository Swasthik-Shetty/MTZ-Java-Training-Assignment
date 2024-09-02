package com.monetize360.contactapp.service;

import com.monetize360.contactapp.dto.UserDto;

import java.util.UUID;

public interface UserService {
    public UserDto getUserById(UUID uuid);
}
