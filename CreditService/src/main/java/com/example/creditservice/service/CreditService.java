package com.example.creditservice.service;

import com.example.creditservice.dto.LoanOrderCreateDto;
import com.example.creditservice.dto.LoanOrderDeleteDto;
import com.example.creditservice.dto.LoanOrderDto;
import com.example.creditservice.dto.TariffDto;

import java.util.List;
import java.util.UUID;

public interface CreditService {

    List<TariffDto> getTariffs();

    List<LoanOrderDto> getUserOrders(Long userId);

    LoanOrderDto createOrder(LoanOrderCreateDto dto);

    LoanOrderDto getStatusOrder(UUID orderId);

    void deleteOrder(LoanOrderDeleteDto dto);


}
