package com.example.mtsfinalprojectsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tariff {

    private Long id;

    private String type;

    private String interestRate;
}
