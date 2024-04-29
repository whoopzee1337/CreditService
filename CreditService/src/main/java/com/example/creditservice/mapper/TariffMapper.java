package com.example.creditservice.mapper;

import com.example.creditservice.dto.TariffDto;
import com.example.creditservice.entity.TariffEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TariffMapper {

    TariffDto toDto(TariffEntity entity);

}
