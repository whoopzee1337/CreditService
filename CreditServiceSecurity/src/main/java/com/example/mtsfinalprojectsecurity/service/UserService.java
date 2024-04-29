package com.example.mtsfinalprojectsecurity.service;

import com.example.mtsfinalprojectsecurity.dto.UserDto;

public interface UserService {

    void saveUser(UserDto dto);

    UserDto getUserByUsername(String username);

}
