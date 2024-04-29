package com.example.mtsfinalprojectsecurity.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ErrorResponse {

    private String code;

    private String message;
}
