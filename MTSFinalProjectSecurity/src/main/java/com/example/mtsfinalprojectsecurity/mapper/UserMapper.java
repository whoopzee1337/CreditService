package com.example.mtsfinalprojectsecurity.mapper;

import com.example.mtsfinalprojectsecurity.dto.UserDto;
import com.example.mtsfinalprojectsecurity.entity.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    UserDto toDto(UserEntity entity);

    UserEntity toEntity(UserDto dto);
}
