package com.example.mtsfinalprojectsecurity.model;

import com.example.mtsfinalprojectsecurity.dto.LoanOrderDto;
import lombok.*;

import java.util.List;

@Data
public class DataResponse {

    private List<Tariff> tariffs;

    private String orderId;

    private String orderStatus;

    private List<LoanOrderDto> orders;

}
