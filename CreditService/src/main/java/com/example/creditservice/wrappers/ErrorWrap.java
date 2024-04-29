package com.example.creditservice.wrappers;

import com.example.creditservice.exeptionshandle.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorWrap {
    private ErrorResponse error;
}
