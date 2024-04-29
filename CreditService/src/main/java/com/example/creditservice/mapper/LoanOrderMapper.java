package com.example.creditservice.mapper;

import com.example.creditservice.dto.LoanOrderDto;
import com.example.creditservice.entity.LoanOrderEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LoanOrderMapper {

    LoanOrderDto toDto(LoanOrderEntity entity);

}
