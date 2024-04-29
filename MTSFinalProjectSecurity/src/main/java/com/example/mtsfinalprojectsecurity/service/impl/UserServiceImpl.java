package com.example.mtsfinalprojectsecurity.service.impl;

import com.example.mtsfinalprojectsecurity.dto.UserDto;
import com.example.mtsfinalprojectsecurity.entity.UserEntity;
import com.example.mtsfinalprojectsecurity.mapper.UserMapper;
import com.example.mtsfinalprojectsecurity.repository.UserRepository;
import com.example.mtsfinalprojectsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper mapper;
    @Override
    public void saveUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername())
                .setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()))
                .setRole("ROLE_USER");
        userRepository.save(userEntity);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return mapper.toDto(userRepository.findBy(username));
    }


}