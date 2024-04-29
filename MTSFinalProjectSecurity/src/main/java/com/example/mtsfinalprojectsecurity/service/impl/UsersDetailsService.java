package com.example.mtsfinalprojectsecurity.service.impl;

import com.example.mtsfinalprojectsecurity.entity.UserEntity;
import com.example.mtsfinalprojectsecurity.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsersDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findBy(username);
        if (username == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userEntity;
    }
}
