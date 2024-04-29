package com.example.mtsfinalprojectsecurity.service;

import com.example.mtsfinalprojectsecurity.dto.LoanOrderCreateDto;
import com.example.mtsfinalprojectsecurity.dto.LoanOrderDeleteDto;
import com.example.mtsfinalprojectsecurity.model.Response;

public interface WebClientService {

    Response getTariffs();

    Response getStatus(String orderId);

    Response deleteOrder(LoanOrderDeleteDto dto);

    Response createOrder(LoanOrderCreateDto dto);

    Response getUserOrders(Long userId);

}
