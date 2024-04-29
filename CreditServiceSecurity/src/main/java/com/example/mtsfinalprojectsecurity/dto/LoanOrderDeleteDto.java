package com.example.mtsfinalprojectsecurity.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class LoanOrderDeleteDto {

    private Long userId;

    private UUID orderId;
}
