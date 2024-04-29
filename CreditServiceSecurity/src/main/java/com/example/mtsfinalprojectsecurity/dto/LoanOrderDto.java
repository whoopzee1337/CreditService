package com.example.mtsfinalprojectsecurity.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class LoanOrderDto {

    private Long id;

    private UUID orderId;

    private Long userId;

    private Long tariffId;

    private Double creditRating;

    private String status;

    private Timestamp timeInsert;

    private Timestamp timeUpdate;
}
