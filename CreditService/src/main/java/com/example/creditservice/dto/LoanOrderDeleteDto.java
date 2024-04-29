package com.example.creditservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class LoanOrderDeleteDto {

    private Long userId;

    private UUID orderId;
}
