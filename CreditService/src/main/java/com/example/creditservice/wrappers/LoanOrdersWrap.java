package com.example.creditservice.wrappers;

import com.example.creditservice.dto.LoanOrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoanOrdersWrap {
    private List<LoanOrderDto> orders;
}
