package com.example.mtsfinalprojectsecurity.repository;

import com.example.mtsfinalprojectsecurity.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_USER_BY_USERNAME = "SELECT * FROM USERS WHERE USERNAME = ?";

    private static final String SQL_INSERT = "INSERT INTO USERS(USERNAME, PASSWORD, ROLE) VALUES (?, ?, ?)";

    public UserEntity findBy(String username) {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_USERNAME, new BeanPropertyRowMapper<>(UserEntity.class), username);
    }

    public void save(UserEntity entity) {
        jdbcTemplate.update(SQL_INSERT,
                entity.getUsername(),
                entity.getPassword(),
                entity.getRole());
    }

}
