package com.example.creditservice.wrappers;

import com.example.creditservice.dto.TariffDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TariffsWrap {
    private List<TariffDto> tariffs;
}
